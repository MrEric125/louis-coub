package com.web;

import com.KafkaAdminServiceImpl;
import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.louis.common.common.HttpResult;
import com.louis.kafka.common.Message;
import com.louis.kafka.producer.LouisKafkaProducerImpl;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * @author John·Louis
 * @date create in 2019/10/3
 * description:
 */
@Slf4j
@RestController
public class KafkaController implements ApplicationContextAware {

    private ApplicationContext applicationContext;


    @Value("${kafka.topic}")
    private String topic;


    @Autowired
    private KafkaAdminServiceImpl kafkaAdminService;

    @Autowired(required = true)
    private LouisKafkaProducerImpl<String, String> kafkaSender;

    @RequestMapping("batchSendTopic")
    public HttpResult batchSendToTopic(@RequestParam("topic") String topic) throws Exception{

        List<Message<String,String>> messages = Lists.newArrayList();
        for (int i = 0; i < 100; i++) {
            Message<String, String> message = new Message<>();
            message.setTopic(topic);
            message.setValue(i+"--"+ UUID.randomUUID());
            message.setSendTime(new Date());
            message.setKey(i + "");
            message.setTopic(topic);
            messages.add(message);
        }
        log.info("info:{}", "sende message");
        return HttpResult.ok(kafkaSender.send(messages));
    }

    @RequestMapping("sendTopic")
    public HttpResult sentKafkaToTopic(@RequestParam("param") String param, @RequestParam("topic") String pTopic) throws Exception {
        Message<String, String> message = new Message<>();
        message.setTopic(pTopic);
        message.setValue(param);
        message.setSendTime(new Date());
        String topic = pTopic;

        boolean exist = kafkaAdminService.isExist(topic);

        String send;
        if (exist) {
            message.setTopic(topic);
            log.info("已存在：:{}", JSON.toJSONString(message));

            send = kafkaSender.send(message);
        } else {

            boolean status = kafkaAdminService.createTopic(topic, 1, (short) 1);


            DynamicEvent dynamicEvent = new DynamicEvent();
            dynamicEvent.setDynamicTopic(topic);
            dynamicEvent.setTopic(pTopic);

            kafkaSender.send("create_topic", JSON.toJSONString(dynamicEvent));

            log.info("创建：:{}", JSON.toJSONString(message));

            message.setTopic(topic);
            send = kafkaSender.send(message);
        }
        log.info("info:{}", JSON.toJSONString(message));

        return HttpResult.ok(send);
    }


    @RequestMapping("send")
    public HttpResult sentKafka(@RequestBody Event param) {
        Message<String, String> message = new Message<>();
        message.setTopic(topic);
        String s = JSON.toJSONString(param);
        message.setValue(s);

        message.setSendTime(new Date());
        String send = kafkaSender.send(message);
        return HttpResult.ok(send);
    }


    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @Data
    public static class Event {
        private String fkId;
        /**
         * fkType=1 meeting
         */
        private Integer fkType;

        private Long taskId;

        private String taskNum;
        /**
         * 1:新增
         * 2:更新
         */
        private int opType;

        /**
         * 是否由会议调用群发服务创建
         */
        private boolean createByMeeting;
    }

    @Data
    private class DynamicEvent {
        private String topic;

        private String dynamicTopic;

    }

}
