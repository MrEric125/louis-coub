package com;

import com.google.common.collect.Sets;
import com.louis.kafka.common.AuthInfo;
import com.louis.kafka.producer.LouisKafkaProducerImpl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WebConfig {

    @Value("${kafka.topic}")
    private String topic;

    @Value("${kafka.bootstrap.servers}")
    private String brokers;

    @Bean
    public LouisKafkaProducerImpl<String,String> louisKafkaProducer() {
        LouisKafkaProducerImpl<String,String> louisKafkaProducer = new LouisKafkaProducerImpl<>();

        louisKafkaProducer.setTopics(Sets.newHashSet(topic));
        AuthInfo authInfo = new AuthInfo();
        authInfo.setServerAddr(brokers);
        louisKafkaProducer.setAuthInfo(authInfo);

        return louisKafkaProducer;
    }
}
