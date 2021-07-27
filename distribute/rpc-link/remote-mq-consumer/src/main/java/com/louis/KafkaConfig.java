package com.louis;


import com.louis.kafka.LouisKafkaConsumerImpl;
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

    @Value("${kafka.bootstrap.servers}")
    public String bootstrapServer;

    @Autowired
    private LouisMessageHandler louisMessageHandler;


    @Bean
    public LouisKafkaConsumerImpl<String,String> kafkaConsumer() throws Exception {
        LouisKafkaConsumerImpl<String,String> kafkaConsumer = new LouisKafkaConsumerImpl<>();
        kafkaConsumer.setGroup(groupId);
        kafkaConsumer.setTopic(topic);
        AuthInfo authInfo = new AuthInfo();
        authInfo.setServerAddr(bootstrapServer);

        kafkaConsumer.setAuthInfo(authInfo);
        kafkaConsumer.setMessageHandler(louisMessageHandler);
        kafkaConsumer.doInit();

        kafkaConsumer.start();

        return kafkaConsumer;
    }

}
