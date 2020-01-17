package com.louis.kafka;

import com.louis.common.common.KeyValue;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.time.Duration;
import java.util.Arrays;
import java.util.Optional;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * @author louis
 * <p>
 * Date: 2019/9/23
 * Description:
 */
@Service
@Slf4j
public class MessageConsumer {



    private static BlockingQueue<KeyValue<Long,String>> messageQueue = new ArrayBlockingQueue<>(100);

    private static BlockingQueue<String> stringMessageQueue = new ArrayBlockingQueue<>(100);
    @Autowired
    private KafkaConsumer<Long,String> kafkaConsumer;

    @Value("${kafka.topic.base}")
    private String baseTopic;

    @PostConstruct
    public void init() {
        kafkaConsumer.subscribe(Arrays.asList(baseTopic.split(",")));

    }


    @KafkaListener(topics = "louis", groupId = "louis", containerFactory = "batchContainerFactory")
    public void consumer(String record) {
        System.out.println("i will consumer message");
        stringMessageQueue.offer(record);
        System.out.println("i have consumer message ");
    }

//    todo 之后看能不能将其改为事件监控型的
    public void defaultMessageConsumer() {

        ConsumerRecords<Long,String> records = kafkaConsumer.poll(Duration.ofMillis(1000));
        log.info("total consumer data count:{}", records.count());
        Optional.ofNullable(records).ifPresent(consumerRecords -> {
            records.forEach(record -> messageQueue.offer(new KeyValue<>(record.key(), record.value())));
        });
        kafkaConsumer.commitAsync();

    }


    public KeyValue<Long, String> getQueueMessage() {
        return messageQueue.poll();
    }
    public String getStringQueueMessage() {
        return stringMessageQueue.poll();
    }


}
