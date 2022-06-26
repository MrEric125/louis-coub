package com.web;

import com.alibaba.fastjson.JSON;
import com.louis.common.common.HttpResult;
import com.louis.kafka.common.Message;
import com.louis.kafka.producer.LouisKafkaProducerImpl;
import lombok.Data;
import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.KafkaAdminClient;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.kafka.core.KafkaAdmin;
import org.springframework.web.bind.annotation.RequestBody;
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
public class KafkaController implements ApplicationContextAware {

    private ApplicationContext applicationContext;


    @Value("${kafka.topic}")
    private String topic;

    @Autowired
    private AdminClient adminClient;





    @Autowired(required = true)
    private LouisKafkaProducerImpl kafkaSender;



    @RequestMapping("sendTopic")
    public HttpResult sentKafkaToTopic(@RequestParam String param,@RequestParam String pTopic) {
        Message<String,String> message = new Message<>();
        message.setTopic(pTopic);
        message.setValue(param);
        message.setSendTime(new Date());
        String send = kafkaSender.send(message);
        return HttpResult.ok(send);
    }




    @RequestMapping("send")
    public HttpResult sentKafka(@RequestBody Event param) {
        Message<String,String> message = new Message<>();
        message.setTopic(topic);
        String s = JSON.toJSONString(param);
        message.setValue(s);

        message.setSendTime(new Date());
        String send = kafkaSender.send(message);
        return HttpResult.ok(send);
    }

    @RequestMapping("/topicManager")
    public HttpResult topicManager() {

        return HttpResult.ok(adminClient.listTopics());
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @Data
    public static class Event{
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

}
