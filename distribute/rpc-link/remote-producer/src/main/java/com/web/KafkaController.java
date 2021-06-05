package com.web;

import com.louis.common.common.HttpResult;
import com.louis.kafka.producer.LouisKafkaProducerImpl;
import com.louis.kafka.common.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * @author John·Louis
 * @date create in 2019/10/3
 * description:
 */
@RestController
public class KafkaController {

    @Value("${kafka.topic}")
    private String topic;


    @Autowired
    private LouisKafkaProducerImpl kafkaSender;

    @RequestMapping("send")
    public HttpResult sentKafka(@RequestParam String parm) {
        Message<String,String> message = new Message<>();
        message.setTopic(topic);
        message.setValue(parm);
        message.setSendTime(new Date());
        String send = kafkaSender.send(message);
        return HttpResult.ok(send);
    }
}
