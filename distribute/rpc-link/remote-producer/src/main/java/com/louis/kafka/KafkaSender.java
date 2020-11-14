package com.louis.kafka;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

/**
 * @author John·Louis
 * @date create in 2019/9/21
 * description:
 */
@Component
public class KafkaSender {

    private Gson gson = new GsonBuilder().create();


    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public void sendMessage(Message message) {
        kafkaTemplate.send(message.getTopic(), gson.toJson(message));
    }

}
