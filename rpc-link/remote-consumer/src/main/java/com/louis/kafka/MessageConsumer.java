package com.louis.kafka;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.louis.common.common.WrapMapper;
import com.louis.common.common.Wrapper;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * @author louis
 * <p>
 * Date: 2019/9/23
 * Description:
 */
@Service
public class MessageConsumer {

    String consumerMessage ;


    private static BlockingQueue<String> messageQueue = new ArrayBlockingQueue<>(100);


    @KafkaListener(topics = "louis", groupId = "louis")
    public void consumer(String message) {
        Gson gson = new GsonBuilder().create();
        String s = gson.toJson(message);
        System.out.println("i will consumer message");
        messageQueue.offer(message);
        System.out.println("i have consumer message ");
    }

    public String getQueueMessage() {
        return messageQueue.poll();
    }
}
