package com.louis.config;

import com.louis.kafka.Message;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

/**
 * @author louis
 * <p>
 * Date: 2019/9/23
 * Description:
 */

@Configuration
public class KafkaConsumerConfig {


    public static Properties init() {

        Properties props = new Properties();
        props.put("bootstrap.servers", "localhost:9092");
        props.put("transactional.id", "my-transactional-id");
        return props;

    }

    @Bean
    public KafkaProducer<String, String> kafkaProducer() {
        return new KafkaProducer<String, String>(init(), new StringSerializer(), new StringSerializer());
    }




}
