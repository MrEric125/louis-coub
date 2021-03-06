package com.web;

import com.louis.common.common.HttpResult;
import com.louis.common.common.HttpResult;
import com.louis.kafka.Message;
import com.louis.kafka.MessageProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.Date;
import java.util.Random;

/**
 * @author louis
 * @Date 2020/1/16
 * description:
 */
@RestController
@RequestMapping("/kafka")
public class KafkaProducerController {



    @Autowired
    private MessageProducer<Long, String> kafkaProducer;

    @RequestMapping("send/{topic}")
    public HttpResult sentKafka(@PathVariable(required = false) String topic, @RequestParam String param) {

        Random random = new Random();
        String[] splitTopic = topic.split(",");
        Arrays.stream(splitTopic).forEach(t->{
            Message<Long, String> message = new Message<>();
            Long id = random.nextLong();
            message.setId(id);
            message.setTopic(topic);
            message.setMsg(param);
            message.setSendTime(new Date());
            kafkaProducer.send(message);
        });
        return HttpResult.wrap("success");
    }

}
