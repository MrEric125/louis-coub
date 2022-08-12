package com.louis.kafka;

import com.alibaba.fastjson.JSON;
import com.louis.kafka.common.ClientTemplate;
import com.louis.kafka.common.ClusterInfo;
import com.louis.kafka.common.Constants;
import com.louis.kafka.common.MessageExt;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.io.Serializable;
import java.time.Duration;
import java.util.Collections;
import java.util.Properties;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
public class BaseKafkaConsumerImpl<Key extends Serializable, Value extends Serializable> extends ClientTemplate {

    private String group;

    private String topic;

    protected static volatile boolean restarting = false;

    protected volatile boolean consuming = true;
    protected volatile long consumeStuckThreshold = 60000; // ms

    private KafkaMessageHandler<Key, Value> messageHandler;
    private ConsumerPropertiesHandler propertiesHandler = new ConsumerPropertiesHandler();
    private ExecutorService consumePool;
    private KafkaMsgParse<Key, Value> kafkaMsgParse = new KafkaMsgParse<>();

    private CountDownLatch destroyLatch;
    private volatile boolean mgAutoCommitEnabled = true;

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        if (group != null) {
            group = group.trim();
        }
        this.group = group;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        if (topic != null) {
            topic = topic.trim();
        }
        this.topic = topic;
    }

    public void setMessageHandler(KafkaMessageHandler<Key, Value> kafkaMessageHandler) {
        this.messageHandler = kafkaMessageHandler;
    }


    @Override
    public void doInit() {
        initCluster();
        createConsumer();
    }

    private void initCluster() {
        ClusterInfo clusterInfo = new ClusterInfo();
        if (StringUtils.isBlank(authInfo.getServerAddr())) {
            throw new IllegalArgumentException("kafka 服务器信息不能为空");
        }
        clusterInfo.setBrokers(authInfo.getServerAddr());
        this.clusterInfo = clusterInfo;
    }

    public void start() {
        int consumerNums = getConsumerNums();
        destroyLatch = new CountDownLatch(consumerNums);
        log.info("start [{}] consume, consume size: {} ..", getTopic(), consumerNums);
        Random random = new Random();
        for (int i = 0; i < consumerNums; i++) {
            final int flag = i + 1;
            String clientId = String.format("%s-%s-%s-%s",
                    Constants.DEF_CLIENT_ID_VAL, getTopic(), random.nextInt(1000), flag);
            properties.put(Constants.KafkaConsumerConstant.CLIENT_ID_NAME, clientId);
            log.info("clientId:{}", clientId);
            final KafkaConsumer<byte[], byte[]> consumer = new KafkaConsumer<>(properties);

//            Set<String> topics = consumer.listTopics().keySet();
//
//            log.info("topics:{}", JSON.toJSONString(topics));

            consumer.subscribe(Collections.singletonList(getTopic()));

            consumePool.execute(() -> {
                try {
                    while (consuming) {
                        try {
                            ConsumerRecords<byte[], byte[]> records = consumer.poll(Duration.ofSeconds(10));

                            boolean noMessage = records.isEmpty();
                            if (noMessage) {
                                continue;
                            }

                            int totalSize = 0;
                            for (ConsumerRecord<byte[], byte[]> record : records) {
                                totalSize = totalSize+record.serializedValueSize();
                            }
                            log.info("当前拉取消息数量为：{},总大小为：{}", records.count(), totalSize);


                            for (ConsumerRecord<byte[], byte[]> record : records) {
                                MessageExt<Key, Value> msgVo = null;
                                try {
                                    msgVo = kafkaMsgParse.receiveParse(record, ConsumerRecord.class);
                                    messageHandler.onMessage(msgVo);
                                } catch (Throwable t) {
                                    log.error(String.format("consume error! msgVo: %s"
                                            , msgVo == null ? "" : msgVo.toString()), t);
                                }
                                if (mgAutoCommitEnabled) {
                                    consumer.commitAsync();
                                    checkCommitOffsets(flag);
                                }
                            }

                            if (mgAutoCommitEnabled && noMessage) {
                                checkCommitOffsets(flag);
                            }
                        } catch (Throwable t) {
                            log.error("new version kafka consumer trifles process failed, please concern!", t);
                        }
                    }
                } catch (Throwable throwable) {
                    log.error("consumer error ", throwable);
                } finally {
                    consumer.close();
                    destroyLatch.countDown();
                    log.info("{}:{} consume over!", getTopic(), getGroup());
                }
            });
        }

    }

    private void checkCommitOffsets(int flag) {
//        log.info("flag:{}", flag);


    }

    private void createConsumer() {

        propertiesHandler.init(getGroup(), clusterInfo.getBrokers());
        Properties defaultProperties = propertiesHandler.getProperties();
        defaultProperties.putAll(getProperties());
        this.properties = defaultProperties;

        if (this.properties == null) {
            this.properties = new Properties();
        }
        printStartInfo();

        if (clusterInfo == null || StringUtils.isBlank(clusterInfo.getBrokers())) {
            throw new IllegalArgumentException(" kafka brokers 信息不能为空");
        }

        if (restarting) {
            this.properties.setProperty(Constants.KafkaConsumerConstant.AUTO_OFFSET_RESET_NAME, Constants.KAFKA_20_HEAD);
            log.info("set kafka restart consumer parameter, {} = {}", Constants.KafkaConsumerConstant.AUTO_OFFSET_RESET_NAME,
                    Constants.KAFKA_20_HEAD);
        }
        if (this.properties.getProperty(Constants.KafkaConsumerConstant.BOOTSTRAP_SERVERS_NAME) == null) {
            throw new IllegalArgumentException(String.format(
                    "kafka consumer param %s can not be null!", Constants.KafkaConsumerConstant.BOOTSTRAP_SERVERS_NAME));
        }
        if (this.properties.getProperty(Constants.KafkaConsumerConstant.GROUP_ID_NAME) == null) {
            throw new IllegalArgumentException(String.format(
                    "kafka consumer param %s can not be null!", Constants.KafkaConsumerConstant.GROUP_ID_NAME));
        }
        if (this.properties.getProperty(Constants.KafkaConsumerConstant.CONSUME_STUCK_THRESHOLD_MS_NAME) != null) {
            consumeStuckThreshold = Long.parseLong(this.properties.getProperty(Constants.KafkaConsumerConstant.CONSUME_STUCK_THRESHOLD_MS_NAME));
        }

        int consumerNums = getConsumerNums();
        consumePool = Executors.newFixedThreadPool(consumerNums, new ThreadFactory() {
            private AtomicInteger idx = new AtomicInteger(0);

            public Thread newThread(Runnable r) {
                return new Thread(r, String.format("Kafka-20-Consume-Thread-%s-%s-%d", getTopic(), getGroup(), idx.getAndIncrement()));
            }
        });
        log.info(JSON.toJSONString(this.properties, true));

    }

    private int getConsumerNums() {
        String consumerNumsStr = properties.getProperty(Constants.KafkaConsumerConstant.NUM_CONSUMERS_NAME);
        try {
            return Integer.parseInt(consumerNumsStr);
        } catch (Exception e) {
            log.warn("getConsumerNum parse {} error! {}", consumerNumsStr, e.getMessage());
            return Integer.parseInt(Constants.KafkaConsumerConstant.DEF_NUM_CONSUMERS_VAL);
        }
//        return 10;
    }

    private void printStartInfo() {
    }


}
