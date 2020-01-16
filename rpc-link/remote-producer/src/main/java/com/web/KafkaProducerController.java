package com.web;

import com.louis.common.common.WrapMapper;
import com.louis.common.common.Wrapper;
import com.louis.kafka.Message;
import com.louis.kafka.MessageProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * @author louis
 * @Date 2020/1/16
 * description:
 */
@RestController
@RequestMapping("/kafka")
public class KafkaProducerController {

    @Value("${kafka.topic}")
    private String topic;

    @Autowired
    private MessageProducer<Long, String> kafkaProducer;

    @RequestMapping("send")
    public Wrapper sentKafka(@RequestParam String parm) {
        Message<Long, String> message = new Message<>();
        message.setTopic(topic);
        message.setMsg(parm);
        message.setSendTime(new Date());
        kafkaProducer.send(message);
        return WrapMapper.wrap(message);
    }

}
