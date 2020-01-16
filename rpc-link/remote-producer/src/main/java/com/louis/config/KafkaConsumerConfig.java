package com.louis.config;

import org.apache.curator.framework.schema.Schema;
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
//        todo 一下配置启用事务，事务启用需要注意的事项
//        props.put("transactional.id", "my-transactional-id");
        return props;

    }

    @Bean
    public KafkaProducer kafkaProducer() {
        String schemaString = "";
        Schema schema = Schema.builder(schemaString).build();

        return new KafkaProducer<String, String>(init(), new StringSerializer(), new StringSerializer());
    }




}
