package com;

import com.google.common.collect.Sets;
import com.louis.kafka.common.AuthInfo;
import com.louis.kafka.producer.LouisKafkaProducerImpl;
import org.apache.kafka.clients.CommonClientConfigs;
import org.apache.kafka.clients.admin.AdminClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

@Configuration
public class KafkaConfig {

    @Value("${kafka.topic}")
    private String topic;

    @Value("${kafka.bootstrap.servers}")
    private String brokers;

    @Bean("louisKafkaProducer")
    public LouisKafkaProducerImpl<String,String> louisKafkaProducer() throws Exception{
        LouisKafkaProducerImpl<String,String> louisKafkaProducer = new LouisKafkaProducerImpl<>();

        louisKafkaProducer.setTopics(Sets.newHashSet(topic));
        AuthInfo authInfo = new AuthInfo();
        authInfo.setServerAddr(brokers);
        louisKafkaProducer.setAuthInfo(authInfo);
        louisKafkaProducer.doInit();

        return louisKafkaProducer;
    }

    @Bean
    public AdminClient kafkaAdminClient() {
        Properties props = new Properties();
        props.put(CommonClientConfigs.BOOTSTRAP_SERVERS_CONFIG, brokers);

        return AdminClient.create(props);
    }
}
