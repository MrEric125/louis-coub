package com.louis.kafka;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

/**
 * @author louis
 * <p>
 * Date: 2019/9/23
 * Description:
 */
@Service
public class MessageConsumer {

    String consumerMessage ;


    @KafkaListener(topics = "louis",groupId = "louis")
    public String consumer(Message message) {
        Gson gson = new GsonBuilder().create();
        String s = gson.toJson(message);
        System.out.println("i will consumer message");
        System.out.println(s);
        System.out.println("i have consumer message ");
        consumerMessage = s;
        return consumerMessage;
    }
}
