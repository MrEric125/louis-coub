package com.louis.kafka;

import com.louis.kafka.common.ClientTemplate;
import com.louis.kafka.common.Constants;
import com.louis.kafka.common.MessageExt;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.time.Duration;
import java.util.Arrays;
import java.util.Properties;
import java.util.Random;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.locks.ReentrantLock;

@Slf4j
public class BaseKafkaConsumerImpl extends ClientTemplate {

    private String group;

    private String topic;

    /**
     * consumer restart always set:
     *  auto.offset.reset=smallest (kafka 0.8)
     *  auto.offset.reset=earliest (kafka 2.0)
     *  setConsumeFromWhere(CONSUME_FROM_FIRST_OFFSET) (RocketMQ)
     */
    protected static volatile boolean restarting = false;

    protected volatile boolean shutdownWithCommitOffset = false;
    protected volatile boolean consuming = true;
    protected volatile long lastCommitTime = System.currentTimeMillis();
    protected AtomicLong commitCounter = new AtomicLong(0);
    protected ReentrantLock commitLock = new ReentrantLock();
    protected volatile int messageFlying = 0;
    protected ReentrantLock flyingLock = new ReentrantLock();
    protected ConcurrentHashMap<Integer, FlyingStat> flyStatsMap = new ConcurrentHashMap<>();
    protected volatile long consumeStuckThreshold = 60000; // ms
    private ScheduledExecutorService statTimer;

    private KafkaMessageHandler messageHandler;
    private ExecutorService consumePool;
    private KafkaMsgParse kafkaMsgParse;

    private CountDownLatch destroyLatch;
    private volatile boolean dmgAutoCommitEnabled = true;

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


    @Override
    public void doInit() throws Exception {
        createConsumer();

    }
    public void start() {
        int consumerNums = getConsumerNums();
        destroyLatch = new CountDownLatch(consumerNums);
        log.info("start [{}] consume, consume size: {} ..", getTopic(), consumerNums);
        Random random = new Random();
        for (int i=0; i<consumerNums; i++) {
            final int flag = i + 1;
            String clientId = String.format("%s-%s-%s-%s",
                    Constants.DEF_CLIENT_ID_VAL, getTopic(), random.nextInt(1000), flag);
            properties.put(Constants.KafkaConsumerConstant.CLIENT_ID_NAME, clientId);
            final KafkaConsumer<byte[], byte[]> consumer = new KafkaConsumer<>(properties);
            consumer.subscribe(Arrays.asList(getTopic()));
//            consumerMap.put(flag, consumer);

            final FlyingStat stat = new FlyingStat(clientId);
//            final AckService ackService = new AckService(getTopic(), dmgConfigs, flag) {
//                @Override
//                public void ack() {
//                    acquire();
//                    realCommitOffset(stat, consumer);
//                }
//            };
//            flyStatsMap.put(flag, stat);

            consumePool.execute(() -> {
                try {
                    while (consuming) {
                        try {
                            // poll(Duration) need java 8
                            ConsumerRecords<byte[], byte[]> records = consumer.poll(Duration.ofSeconds(6));

                            boolean noMessage = records.isEmpty();
                            for (ConsumerRecord<byte[], byte[]> record: records) {
                                MessageExt<String,String> msgVo = null;
                                try {
                                    msgVo = kafkaMsgParse.receiveParse(record, ConsumerRecord.class);
//                                    messageFlying(flag, msgVo);
                                    messageHandler.onMessage(msgVo);
                                } catch (Throwable t) {
                                    log.error(String.format("consume error! msgVo: %s"
                                            , msgVo == null ? "" : msgVo.toString()), t);
                                }
//                                finally {
//                                    messageLanded(flag);
//                                }

                                if (dmgAutoCommitEnabled) {
                                    checkCommitOffsets(flag);
                                }
                            }

                            // commit check when no new message
                            if (dmgAutoCommitEnabled && noMessage) {
                                checkCommitOffsets(flag);
                            }

//                            blockConsumeIfNeed(consumer);
                        } catch (Throwable t) {
                            log.error("new version kafka consumer trifles process failed, please concern!", t);
                        }
                    }
                } finally {
                    consumer.close();
                    destroyLatch.countDown();
                    log.info("{}:{} consume over!", getTopic(), getGroup());
                }
            });
        }

//        flyStatCheck();
    }

    private void checkCommitOffsets(int flag) {
//        FlyingStat stat = flyStatsMap.get(flag);
//        long counter = stat.getAndAddCommitCounter();
//        long lastCommit = stat.getLastCommitTime();
//
//        if (stat.inflight()) {
//            return;
//        }
//
//        if (counter > Constants.KafkaConsumerConstant.AUTO_COMMIT_OFFSET_SIZE_NEW
//                || System.currentTimeMillis() - lastCommit > Constants.KafkaConsumerConstant.AUTO_COMMIT_OFFSET_TIME_MS * 1000) {
//            this.realCommitOffset(stat, consumerMap.get(flag));
//        }
    }

    private void createConsumer() {
        if (properties == null) {
            properties = new Properties();
        }
        printStartInfo();
//        PropsUtil.printProps(properties);

        properties.setProperty(Constants.KafkaConsumerConstant.GROUP_ID_NAME, getGroup());
        properties.setProperty(Constants.KafkaConsumerConstant.BOOTSTRAP_SERVERS_NAME, clusterInfo.getBrokers());
        // force config
        properties.setProperty(Constants.KafkaConsumerConstant.ENABLE_AUTO_COMMIT_NAME, Constants.KafkaConsumerConstant.ENABLE_AUTO_COMMIT_VAL);
        properties.setProperty(Constants.KafkaConsumerConstant.ALLOW_AUTO_CREATE_TOPICS_NAME, Constants.KafkaConsumerConstant.ALLOW_AUTO_CREATE_TOPICS_VAL);

        fillEmptyPropWithDefVal(properties, Constants.KafkaConsumerConstant.NEW_KEY_DESERIALIZER_NAME, Constants.KafkaConsumerConstant.DEF_KEY_NEW_DESERIALIZER_VAL);
        fillEmptyPropWithDefVal(properties, Constants.KafkaConsumerConstant.NEW_VALUE_DESERIALIZER_NAME, Constants.KafkaConsumerConstant.DEF_VALUE_NEW_DESERIALIZER_VAL);
        fillEmptyPropWithDefVal(properties, Constants.KafkaConsumerConstant.MAX_PARTITION_FETCH_BYTES_NAME, Constants.KafkaConsumerConstant.MAX_PARTITION_FETCH_BYTES_VAL);
        fillEmptyPropWithDefVal(properties, Constants.KafkaConsumerConstant.REBALANCE_BACKOFF_MS_NAME, Constants.KafkaConsumerConstant.DEF_REBALANCE_BACKOFF_MS_VAL);
        fillEmptyPropWithDefVal(properties, Constants.KafkaConsumerConstant.ISOLATION_LEVEL_NAME, Constants.KafkaConsumerConstant.ISOLATION_LEVEL_VAL);
        fillEmptyPropWithDefVal(properties, Constants.KafkaConsumerConstant.NUM_CONSUMERS_NAME, Constants.KafkaConsumerConstant.DEF_NUM_CONSUMERS_VAL);

        if (restarting) {
            properties.setProperty(Constants.KafkaConsumerConstant.AUTO_OFFSET_RESET_NAME, Constants.KAFKA_20_HEAD);
            log.info("set kafka restart consumer parameter, {} = {}", Constants.KafkaConsumerConstant.AUTO_OFFSET_RESET_NAME,
                    Constants.KAFKA_20_HEAD);
        }
        if (properties.getProperty(Constants.KafkaConsumerConstant.BOOTSTRAP_SERVERS_NAME) == null) {
            throw new IllegalArgumentException(String.format(
                    "kafka consumer param %s can not be null!", Constants.KafkaConsumerConstant.BOOTSTRAP_SERVERS_NAME));
        }
        if (properties.getProperty(Constants.KafkaConsumerConstant.GROUP_ID_NAME) == null) {
            throw new IllegalArgumentException(String.format(
                    "kafka consumer param %s can not be null!", Constants.KafkaConsumerConstant.GROUP_ID_NAME));
        }
        if (properties.getProperty(Constants.KafkaConsumerConstant.CONSUME_STUCK_THRESHOLD_MS_NAME) != null) {
            consumeStuckThreshold = Long.parseLong(properties.getProperty(Constants.KafkaConsumerConstant.CONSUME_STUCK_THRESHOLD_MS_NAME));
        }

        // 2. other stuff
        int consumerNums = getConsumerNums();
        consumePool = Executors.newFixedThreadPool(consumerNums, new ThreadFactory() {
            private AtomicInteger idx = new AtomicInteger(0);
            @Override
            public Thread newThread(Runnable r) {
                return new Thread(r, String.format("Kafka-20-Consume-Thread-%s-%s-%d", getTopic(), getGroup(), idx.getAndIncrement()));
            }
        });

    }
    private int getConsumerNums() {
        String consumerNumsStr = properties.getProperty(Constants.KafkaConsumerConstant.NUM_CONSUMERS_NAME);
        try {
            return Integer.parseInt(consumerNumsStr);
        } catch (Exception e) {
            log.warn("getConsumerNum parse {} error! {}", consumerNumsStr, e.getMessage());
            return Integer.parseInt(Constants.KafkaConsumerConstant.DEF_NUM_CONSUMERS_VAL);
        }
    }

    private void printStartInfo() {
    }

    private void fillEmptyPropWithDefVal(Properties props, String pname, String defVal) {
        Object originVal = props.get(pname);
        String realVal = originVal == null ? defVal : ((String) originVal).trim();
        props.put(pname, realVal);
    }


    public static class FlyingStat {
        private volatile long offset;
        private volatile int partition;
        private volatile long lastWarnTime;
        private AtomicLong startTime = new AtomicLong(0);

        /**
         * properties for kafka version > 2.0
         */
        private String clientId;
        private AtomicLong lastCommitTime = new AtomicLong(System.currentTimeMillis());
        private AtomicLong commitCounter = new AtomicLong(0);

        private ReentrantLock lock = new ReentrantLock();

        FlyingStat() { }
        FlyingStat(String clientId) {
            this.clientId = clientId;
        }

        /**
         * BUG FIX: reset() and cost() invoke has concurrency problem.
         */
        public void reset() {
            lock.lock();
            try {
                offset = 0;
                partition = -1;
                startTime.set(0);
            } finally {
                lock.unlock();
            }

        }

        public void start() {
            startTime.set(System.currentTimeMillis());
        }

        public long cost() {
            lock.lock();
            try {
                if (startTime.get() == 0) {
                    return 0;
                }
                return System.currentTimeMillis() - startTime.get();
            } finally {
                lock.unlock();
            }
        }

        public boolean inflight() {
            return startTime.get() > 0;
        }

        public long getOffset() {
            return offset;
        }

        public void setOffset(long offset) {
            this.offset = offset;
        }

        public int getPartition() {
            return partition;
        }

        public void setPartition(int partition) {
            this.partition = partition;
        }

        public long getLastWarnTime() {
            return lastWarnTime;
        }

        public void setLastWarnTime(long lastWarnTime) {
            this.lastWarnTime = lastWarnTime;
        }

        public String getClientId() {
            return clientId;
        }

        public void setClientId(String clientId) {
            this.clientId = clientId;
        }

        public long getLastCommitTime() {
            return lastCommitTime.get();
        }

        public void setLastCommitTime(long lastCommitTime) {
            this.lastCommitTime.set(lastCommitTime);
        }

        public long getCommitCounter() {
            return commitCounter.get();
        }

        public void setCommitCounter(long commitCounter) {
            this.commitCounter.set(commitCounter);
        }

        public long getAndAddCommitCounter() {
            return this.commitCounter.getAndIncrement();
        }
    }
}
