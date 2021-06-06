package com.louis.config;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.LongDeserializer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * @author John·Louis
 * @date create in 2019/11/17
 * description:
 */
//@Configuration
public class KafKaConsumerConfig {



    @Value("${kafka.bootstrap.servers}")
    private String bootstrapServers;

    @Value("${kafka.enable.auto.commit.config}")
    private Integer autoCommitConfig;

    @Value("${kafka.auto.commit.interval.ms.config}")
    private Integer autoComitInterval;

    @Value("${kafka.session.timeout.ms.config}")
    private Integer sessionTimeoutMs;

    @Value("${kafka.max.poll.records.config}")
    private Integer maxPollRecords;

    @Value("${kafka.groupId}")
    private String groupId;




//    批量消费配置
    private Map<String, Object> consumerProps() {
        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.GROUP_ID_CONFIG, groupId);
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,bootstrapServers);
        props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, autoCommitConfig==1);
        props.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG, autoComitInterval);
        props.put(ConsumerConfig.SESSION_TIMEOUT_MS_CONFIG, sessionTimeoutMs);
        //一次拉取消息数量
        props.put(ConsumerConfig.MAX_POLL_RECORDS_CONFIG, maxPollRecords);
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, LongDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        return props;
    }
    @Bean("batchContainerFactory")
    public ConcurrentKafkaListenerContainerFactory ackContainerFactory() {
         ConcurrentKafkaListenerContainerFactory factory = new ConcurrentKafkaListenerContainerFactory();
         factory.setConsumerFactory(new DefaultKafkaConsumerFactory(consumerProps()));
         factory.setBatchListener(true);
         factory.setConcurrency(maxPollRecords);
         return factory;
    }
    @Bean
    public KafkaConsumer kafkaConsumer() {
        return new KafkaConsumer(consumerProps());
    }

}
