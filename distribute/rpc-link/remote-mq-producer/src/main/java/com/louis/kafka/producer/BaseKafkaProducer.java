package com.louis.kafka.producer;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Preconditions;
import com.louis.kafka.common.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.kafka.clients.producer.*;
import org.apache.kafka.common.serialization.LongSerializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.assertj.core.util.Sets;

import java.io.Serializable;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.stream.Collectors;

/**
 * kafka 生产者基类，主要实现初始化一些基本操作，
 * @param <Key>
 * @param <Value>
 */
@Slf4j
public class BaseKafkaProducer<Key extends Serializable, Value extends Serializable> extends ClientTemplate {



    private KafkaProducer<Key, Value> kafkaProducer;

    protected Set<String> topics;

    public Set<String> getTopics() {
        return topics;
    }

    public void setTopics(Set<String> topics) {
        if (CollectionUtils.isNotEmpty(topics)) {
            this.topics = topics.stream().map(String::trim).collect(Collectors.toSet());
            return;
        }
        this.topics = Sets.newHashSet();
    }

    @Override
    public void doInit() throws Exception {

        Preconditions.checkNotNull(topics, "topic");
        initContext();
        initClusterInfo();
        setUpProperties();
        initProducer();
    }

    private void initProducer() throws Exception {
        this.kafkaProducer = createProducer(clusterInfo);

    }

    private KafkaProducer<Key,Value> createProducer(ClusterInfo clusterInfo) throws Exception {
        if (clusterInfo == null) {
            throw new KafkaInitException("kafka 初始化没有配置信息");
        }
        return new KafkaProducer<Key, Value>(properties);

    }

    private void initContext() {
    }

    private void initClusterInfo(){
        ClusterInfo clusterInfo = new ClusterInfo();
        AuthInfo authInfo = getAuthInfo();
        clusterInfo.setBrokers(authInfo.getServerAddr());
        super.setClusterInfo(clusterInfo);

    }
    private void setUpProperties() {
        if (properties == null) {
            properties = new Properties();
        }
        printStartInfo();

        checkSerializer();
        fillEmptyPropWithDefVal(properties, Constants.KafkaProducerConstant.BOOTSTRAP_SERVERS_NAME, clusterInfo.getBrokers());
        fillEmptyPropWithDefVal(properties, Constants.KafkaProducerConstant.METADATA_MAX_AGE_MS, String.valueOf(60000));
        fillEmptyPropWithDefVal(properties, Constants.KafkaProducerConstant.ACKS_NAME, Constants.KafkaProducerConstant.DEF_ACKS_VAL);
        fillEmptyPropWithDefVal(properties, Constants.KafkaProducerConstant.COMPRESSION_TYPE_NAME, Constants.KafkaProducerConstant.DEF_COMPRESSION_TYPE_VAL);
        fillEmptyPropWithDefVal(properties, Constants.KafkaProducerConstant.RETRIES_NAME, Constants.KafkaProducerConstant.DEF_RETRIES_VAL);
        fillEmptyPropWithDefVal(properties, Constants.KafkaProducerConstant.CLINET_ID_NAME, genProducerClientId());
        fillEmptyPropWithDefVal(properties, Constants.KafkaProducerConstant.TIMEOUT_MS_NAME, Constants.KafkaProducerConstant.DEF_TIMEOUT_MS_VAL);
        fillEmptyPropWithDefVal(properties, Constants.KafkaProducerConstant.RECONNECT_BACKOFF_MS_NAME, Constants.KafkaProducerConstant.DEF_RECONNECT_BACKOFF_MS_VAL);
        fillEmptyPropWithDefVal(properties, Constants.KafkaProducerConstant.RETRY_BACKOFF_MS_NAME, Constants.KafkaProducerConstant.DEF_RETRY_BACKOFF_MS_VAL);
        fillEmptyPropWithDefVal(properties, Constants.KafkaProducerConstant.BATCH_SIZE_NAME, Constants.KafkaProducerConstant.DEF_BATCH_SIZE_VAL);
        fillEmptyPropWithDefVal(properties, Constants.KafkaProducerConstant.LINGER_MS_NAME, Constants.KafkaProducerConstant.DEF_LINGER_MS_VAL);
        fillEmptyPropWithDefVal(properties, Constants.KafkaProducerConstant.MAX_REQUEST_SIZE_NAME, Constants.KafkaProducerConstant.MAX_REQUEST_SIZE_VAL);
        log.info(JSON.toJSONString(properties, true));

    }
    private void fillEmptyPropWithDefVal (Properties props, String pname, String defVal) {
        Object originVal = props.get(pname);
        String realVal = originVal == null ? defVal : ((String) originVal).trim();
        props.put(pname, realVal);
    }

    private String genProducerClientId() {
        StringBuilder builder = new StringBuilder(Constants.DEF_CLIENT_ID_VAL).append("@");
        for (String topic:getTopics()) {
            builder.append(topic).append("@");
        }
        return builder.deleteCharAt(builder.length() - 1).toString();
    }

    private void checkSerializer() {

        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
    }
    protected void printStartInfo() {

//        log.info("dmg client info: [{}]; [{}, {}, {}, {}]; [{}, {}, {}]; {}; {} init...","");

    }

    public String send(com.louis.kafka.common.Message<Key, Value> message) throws Exception {
        ProducerRecord<Key, Value> producerRecord = parseMessage(message);
        Future<RecordMetadata> send = this.kafkaProducer.send(producerRecord);
        RecordMetadata recordMetadata = send.get();
        long offset = recordMetadata.offset();
        return String.valueOf(offset);

    }

    private String sendSync(com.louis.kafka.common.Message<Key, Value> message, KafkaProducer<Key, Value> producer, Callback callback) {

        ProducerRecord<Key, Value> producerRecord = parseMessage(message);

        producer.send(producerRecord, callback);

        return "";

    }

    private ProducerRecord<Key,Value> parseMessage(Message<Key,Value> message) {

        ProducerRecord<Key, Value> record = null;
        Integer partition = partition();
        if (partition != null) {
            record = new ProducerRecord<>(message.getTopic(),partition, message.getKey(), message.getValue());
        } else {
            record = new ProducerRecord<>(message.getTopic(), message.getKey(), message.getValue());
        }
        return record;
    }
    private Integer partition() {
        return null;
    }
}
