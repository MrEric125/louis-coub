package com.louis.kafka;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.louis.common.common.KeyValue;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.logging.LogRecord;

/**
 * @author louis
 * <p>
 * Date: 2019/9/23
 * Description:
 */
@Service
public class MessageConsumer {



    private static BlockingQueue<KeyValue<Long,String>> messageQueue = new ArrayBlockingQueue<>(100);

    private static BlockingQueue<String> stringMessageQueue = new ArrayBlockingQueue<>(100);


    @KafkaListener(topics = "louis", groupId = "louis", containerFactory = "batchContainerFactory")
    public void consumer(String record) {
        System.out.println("i will consumer message");
        stringMessageQueue.offer(record);
        System.out.println("i have consumer message ");
    }




    public KeyValue<Long, String> getQueueMessage() {
        return messageQueue.poll();
    }
    public String getStringQueueMessage() {
        return stringMessageQueue.poll();
    }


}
