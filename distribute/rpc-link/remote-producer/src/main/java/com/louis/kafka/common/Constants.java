package com.louis.kafka.common;

import com.louis.kafka.MonitorUtils;

import java.util.HashSet;
import java.util.Set;

public class Constants {

    public static final String CHARSET_FORMAT = "UTF-8";
    public static final String AUTH_TIME_OUT_MS_NAME = "auth.timeout.ms";

    /**
     * message property: unit/cell/traffic group code key name
     */
    public static final String MSG_PROP_ZONE_KEY = "cell";
    /**
     * message property: trace id key name
     */
    public static final String MSG_PROP_TID_KEY = "tid";
    /**
     * message property: vendor id key name
     */
    public static final String MSG_PROP_VID_KEY = "vid";
    /**
     * message property: specific for RocketMQ, original RocketMQ message ID key name
     */
    public static final String MSG_PROP_RMQ_OMID_KEY = "omid";

    public static final String MSG_PROP_FROM_ZONE_KEY = "_ZONE";

    public static final Set<String> BUILT_IN_MSG_HEADERS;

    static {
        BUILT_IN_MSG_HEADERS = new HashSet<>();
//        BUILT_IN_MSG_HEADERS.add(MSG_PROP_TID_KEY);
        BUILT_IN_MSG_HEADERS.add(MSG_PROP_VID_KEY);
//        BUILT_IN_MSG_HEADERS.add(MSG_PROP_RMQ_OMID_KEY);
    }
    /**
     * http header key name
     */
    public static final String HEADER_ZONE_NAME = "zone";
    public static final String HEADER_GROUP_NAME = "group";

    // mark consume client id
    public static final String DEF_CLIENT_ID_VAL = String.format("%s_%s", MonitorUtils.getLocalIP(), MonitorUtils.getPid());
    public static final String DEF_INST_ID_VAL = MonitorUtils.getJvmInstanceCode();

    public static final Integer DEF_RATE_LIMITER_FAST_FAIL_SECS_VAL = 3;


    public abstract static class KafkaProducerConstant {
        public static final String BOOTSTRAP_SERVERS_NAME = "bootstrap.servers"; // broker list

        public static final String ACKS_NAME = "acks";  // default:1
        //public static final String DEF_ACKS_VAL = "-1";
        public static final String DEF_ACKS_VAL = "all";

        public static final String COMPRESSION_TYPE_NAME = "compression.type"; // none, gzip, snappy. default: none
        public static final String DEF_COMPRESSION_TYPE_VAL = "none";

        public static final String RETRIES_NAME = "retries"; // send fail retry, default: 0. none zero may cause message disorder and duplicate
        public static final String DEF_RETRIES_VAL = "3";

        public static final String CLINET_ID_NAME = "client.id"; // for metrics usage

        public static final String TIMEOUT_MS_NAME = "timeout.ms";  // leader server wait for follower sync. default: 30000
        public static final String DEF_TIMEOUT_MS_VAL = "10000";

        public static final String RECONNECT_BACKOFF_MS_NAME = "reconnect.backoff.ms"; // reconnect wait time. default: 10
        public static final String DEF_RECONNECT_BACKOFF_MS_VAL = "50";

        public static final String RETRY_BACKOFF_MS_NAME = "retry.backoff.ms"; // resend wait time. default: 100
        public static final String DEF_RETRY_BACKOFF_MS_VAL = "200";

        public static final String BATCH_SIZE_NAME = "batch.size";
        public static final String DEF_BATCH_SIZE_VAL = "10000"; // default is: 16384

        public static final String LINGER_MS_NAME = "linger.ms"; // default: 0
        public static final String DEF_LINGER_MS_VAL = "1";

        public static final String METADATA_MAX_AGE_MS = "metadata.max.age.ms";

        public static final String MAX_REQUEST_SIZE_NAME = "max.request.size";

        //保持与kafka broker端${message.max.bytes}一致;
        //RecordTooLargeException[org.apache.kafka.common.errors.RecordTooLargeException:
        // The message is 1072970 bytes when serialized which is larger than the maximum request size
        // you have configured with the max.request.size configuration.],
        public static final String MAX_REQUEST_SIZE_VAL = "2097150";

        public static final String KEY_SERIALIZER_NAME = "key.serializer";
        public static final String DEF_KEY_SERIALIZER_VAL = "org.apache.kafka.common.serialization.StringSerializer";
        public static final String DEF_KEY_NEW_SERIALIZER_VAL = "com.louis.kafka.common.serialization.StringSerializer";

        public static final String VALUE_SERIALIZER_NAME = "value.serializer";
        public static final String DEF_VALUE_SERIALIZER_VAL = "org.apache.kafka.common.serialization.ByteArraySerializer";
        public static final String DEF_VALUE_NEW_SERIALIZER_VAL = "com.louis.kafka.common.serialization.ByteArraySerializer";
    }

    public abstract static class KafkaConsumerConstant {

        public static final String GROUP_ID_NAME = "group.id";

        public static final String ZOOKEEPER_CONNECT_NAME = "zookeeper.connect";

        public static final String CONSUMER_ID_NAME = "consumer.id"; //default: null

        public static final String AUTO_COMMIT_ENABLE_NAME = "auto.commit.enable"; // default: true (caution!)
        public static final String DEF_AUTO_COMMIT_ENABLE_VAL = "false";

        public static final String REBALANCE_MAX_RETRIES_NAME = "rebalance.max.retries"; //default: 4
        public static final String DEF_REBALANCE_MAX_RETRIES_VAL = "20";

        public static final String REBALANCE_BACKOFF_MS_NAME = "rebalance.backoff.ms";  // default: 2000
        public static final String DEF_REBALANCE_BACKOFF_MS_VAL = "3000";

        public static final String AUTO_OFFSET_RESET_NAME = "auto.offset.reset";  // options: largest / smallest, default: largest.
        public static final String DEF_AUTO_OFFSET_RESET_VAL = "largest";
        public static final String SMALL_AUTO_OFFSET_RESET_VAL = "smallest";

        public static final String CONSUMER_TIMEOUT_MS_NAME = "consumer.timeout.ms";
        public static final String DEF_CONSUMER_TIMEOUT_MS_VAL = "-1";

        public static final String CLIENT_ID_NAME = "client.id";

        public static final String ZOOKEEPER_SESSION_TIMEOUT_MS_NAME = "zookeeper.session.timeout.ms"; // default: 6000
        public static final String DEF_ZOOKEEPER_SESSION_TIMEOUT_MS_VAL = "60000";

        public static final String ZOOKEEPER_CONNECTION_TIMEOUT_MS_NAME = "zookeeper.connection.timeout.ms"; // default: 6000
        public static final String DEF_ZOOKEEPER_CONNECTION_TIMEOUT_MS_VAL = "60000";

        public static final String OFFSET_STORAGE_NAME = "offsets.storage"; // where is offsets stored. options: zookeeper / kafka, default: zookeeper
        public static final String DEF_OFFSET_STORAGE_VAL = "zookeeper";

        public static final String NUM_CONSUMERS_NAME = "num.consumers";
        public static final String DEF_NUM_CONSUMERS_VAL = "1";

        public static final String FETCH_MESSAGE_MAX_BYTES_NAME = "fetch.message.max.bytes";
        public static final String DEF_FETCH_MESSAGE_MAX_BYTES_VAL = "5242880"; // 1024 * 1024 * 5

        public static final int AUTO_COMMIT_OFFSET_TIME_MS = 30;
        public static final int AUTO_COMMIT_OFFSET_SIZE = 128;

        public static final String DMG_AUTO_COMMIT_ENABLE = "dmg.auto.commit.enable";
        public static final String DEF_DMG_AUTO_COMMIT_ENABLE_VAL = "true";

        public static final String CONSUME_STUCK_THRESHOLD_MS_NAME = "consume.stuck.threshold.ms";


        /**********************************************
         * specific parameters after kafka 2.0
         */
        public static final String BOOTSTRAP_SERVERS_NAME = "bootstrap.servers";

        public static final String MAX_PARTITION_FETCH_BYTES_NAME = "max.partition.fetch.bytes";
        public static final String MAX_PARTITION_FETCH_BYTES_VAL = DEF_FETCH_MESSAGE_MAX_BYTES_VAL; //native default: 1M

        public static final String ALLOW_AUTO_CREATE_TOPICS_NAME = "allow.auto.create.topics";
        public static final String ALLOW_AUTO_CREATE_TOPICS_VAL = "false";

        public static final String ENABLE_AUTO_COMMIT_NAME = "enable.auto.commit";
        public static final String ENABLE_AUTO_COMMIT_VAL = "false";

        public static final String ISOLATION_LEVEL_NAME = "isolation.level";
        public static final String ISOLATION_LEVEL_VAL = "read_committed";

        public static final String CLIENT_RACK_NAME = "client.rack";

        public static final int AUTO_COMMIT_OFFSET_SIZE_NEW = 64;

        public static final String NEW_KEY_DESERIALIZER_NAME = "key.deserializer";
        public static final String DEF_KEY_NEW_DESERIALIZER_VAL = "com.louis.kafka.common.serialization.StringDeserializer";

        public static final String NEW_VALUE_DESERIALIZER_NAME = "value.deserializer";
        public static final String DEF_VALUE_NEW_DESERIALIZER_VAL = "com.louis.kafka.common.serialization.ByteArrayDeserializer";
    }
}
