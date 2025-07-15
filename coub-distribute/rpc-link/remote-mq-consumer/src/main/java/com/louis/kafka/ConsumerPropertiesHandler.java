package com.louis.kafka;

import com.louis.kafka.common.Constants;
import lombok.Getter;
import lombok.Setter;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.util.Properties;

/**
 * @author jun.liu
 * @since 2021/6/21 15:20
 */
@Setter
@Getter
public class ConsumerPropertiesHandler {

    private Properties properties = new Properties();

    public ConsumerPropertiesHandler(Properties properties) {
        this.properties = properties;
    }

    public ConsumerPropertiesHandler() {
    }

    public void init(String group, String brokersName) {
        properties.setProperty(Constants.KafkaConsumerConstant.GROUP_ID_NAME, group);
        properties.setProperty(Constants.KafkaConsumerConstant.BOOTSTRAP_SERVERS_NAME, brokersName);
        properties.setProperty(Constants.KafkaConsumerConstant.ENABLE_AUTO_COMMIT_NAME, Constants.KafkaConsumerConstant.ENABLE_AUTO_COMMIT_VAL);
        properties.setProperty(Constants.KafkaConsumerConstant.ALLOW_AUTO_CREATE_TOPICS_NAME, Constants.KafkaConsumerConstant.ALLOW_AUTO_CREATE_TOPICS_VAL);

        PropertiesUtils.fillEmptyPropWithDefVal(properties, Constants.KafkaConsumerConstant.MAX_PARTITION_FETCH_BYTES_NAME, Constants.KafkaConsumerConstant.MAX_PARTITION_FETCH_BYTES_VAL);
//        PropertiesUtils.fillEmptyPropWithDefVal(properties,Constants.KafkaConsumerConstant.REBALANCE_BACKOFF_MS_NAME, Constants.KafkaConsumerConstant.DEF_REBALANCE_BACKOFF_MS_VAL);
        PropertiesUtils.fillEmptyPropWithDefVal(properties, Constants.KafkaConsumerConstant.ISOLATION_LEVEL_NAME, Constants.KafkaConsumerConstant.ISOLATION_LEVEL_VAL);
        PropertiesUtils.fillEmptyPropWithDefVal(properties, Constants.KafkaConsumerConstant.NUM_CONSUMERS_NAME, Constants.KafkaConsumerConstant.DEF_NUM_CONSUMERS_VAL);

        PropertiesUtils.fillEmptyPropWithDefVal(properties, Constants.KafkaConsumerConstant.CONSUME_FETCH_MIN_BYTES, String.valueOf(1024 * 2));

        properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);

    }


}
