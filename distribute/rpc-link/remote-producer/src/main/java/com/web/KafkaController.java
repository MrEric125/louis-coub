package com.web;

import com.louis.kafka.KafkaSender;
import com.louis.kafka.Message;
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
    private  KafkaSender kafkaSender;

    @RequestMapping("send")
    public String sentKafka(@RequestParam String parm) {
        Message message = new Message();
        message.setTopic(topic);
        message.setMsg(parm);
        message.setSendTime(new Date());
        kafkaSender.sendMessage(message);
        return "success";
    }
}
