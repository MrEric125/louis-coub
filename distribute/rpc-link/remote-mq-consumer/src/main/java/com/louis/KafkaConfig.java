package com.louis;


import com.louis.kafka.BaseKafkaConsumerImpl;
import com.louis.kafka.client.LouisMessageHandler;
import com.louis.kafka.common.AuthInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KafkaConfig {

    @Value("${kafka.groupId}")
    public String groupId;

    @Value("${kafka.topic}")
    public String topic;

    @Value("${kafka.broker}")
    public String broker;

    @Autowired
    private LouisMessageHandler louisMessageHander;


    @Bean
    public BaseKafkaConsumerImpl kafkaConsumer() {
        BaseKafkaConsumerImpl kafkaConsumer = new BaseKafkaConsumerImpl();
        kafkaConsumer.setGroup(groupId);
        kafkaConsumer.setTopic(topic);
        AuthInfo authInfo = new AuthInfo();
        authInfo.setServerAddr(broker);

        kafkaConsumer.setAuthInfo(authInfo);
        kafkaConsumer.setMessageHandler(louisMessageHander);
        kafkaConsumer.start();

        return kafkaConsumer;
    }

}
