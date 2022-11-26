package com.louis.kafka.client;

import com.alibaba.fastjson.JSON;
import com.louis.kafka.KafkaMessageHandler;
import com.louis.kafka.LouisKafkaConsumerImpl;
import com.louis.kafka.client.entity.DynamicEvent;
import com.louis.kafka.common.AuthInfo;
import com.louis.kafka.common.MessageExt;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * @author louis
 * @date 2022/9/7
 */
@Slf4j
@Service
public class CreateTopicMessageHandler implements KafkaMessageHandler<String,String>{

    @Autowired
    private DefaultListableBeanFactory applicationContext;

    @Value("${kafka.groupId}")
    public String groupId;


    @Value("${kafka.bootstrap.servers}")
    public String bootstrapServer;


    @Override
    public void onMessage(MessageExt<String, String> messageExt) throws Exception {


        String value = messageExt.getValue();

        log.info("value :{}", value);

        DynamicEvent dynamicEvent = JSON.parseObject(value, DynamicEvent.class);


        KafkaMessageHandler handler = KafkaMsgHandlerUtils.getHandler(dynamicEvent.getTopic());

        if (Objects.isNull(handler)) {
            return;
        }
        applicationContext.registerSingleton(dynamicEvent.getDynamicTopic() + "_handler", handler);

        LouisKafkaConsumerImpl louisKafkaConsumer = buildConsumerService(dynamicEvent.getDynamicTopic(), handler);
        applicationContext.registerSingleton(dynamicEvent.getDynamicTopic() + "_Service", louisKafkaConsumer);
    }

    public LouisKafkaConsumerImpl buildConsumerService(String topic, KafkaMessageHandler messageHandler) {

        LouisKafkaConsumerImpl<String, String> kafkaConsumer = new LouisKafkaConsumerImpl<>();
        kafkaConsumer.setGroup(groupId);
        kafkaConsumer.setTopic(topic);
        AuthInfo authInfo = new AuthInfo();
        authInfo.setServerAddr(bootstrapServer);
        kafkaConsumer.setAuthInfo(authInfo);
        kafkaConsumer.setMessageHandler(messageHandler);
        kafkaConsumer.doInit();

        kafkaConsumer.start();

        return kafkaConsumer;
    }
}
