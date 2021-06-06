package com.louis.kafka.client;

import com.alibaba.fastjson.JSON;
import com.louis.kafka.KafkaMessageHandler;
import com.louis.kafka.common.MessageExt;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class LouisMessageHandler implements KafkaMessageHandler<String,String> {
    @Override
    public void onMessage(MessageExt<String,String> messageExt) throws Exception{

        log.info("====consumer message=====");
        log.info(JSON.toJSONString(messageExt));

    }
}
