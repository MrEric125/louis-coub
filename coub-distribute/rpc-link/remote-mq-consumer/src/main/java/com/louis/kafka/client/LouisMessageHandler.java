package com.louis.kafka.client;

import com.alibaba.fastjson.JSON;
import com.louis.kafka.KafkaMessageHandler;
import com.louis.kafka.common.MessageExt;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class LouisMessageHandler implements KafkaMessageHandler<String, String>, InitializingBean {

    @Value("${kafka.topic}")
    public String topic;

    @Override
    public void afterPropertiesSet() throws Exception {
        KafkaMsgHandlerUtils.putHandler(topic, this);
    }

    @Override
    public void onMessage(MessageExt<String, String> messageExt) throws Exception {

        log.info("====consumer message=====");
        log.info(JSON.toJSONString(messageExt));

    }
}
