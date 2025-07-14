package com.louis.kafka;

import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collections;
import java.util.List;

/**
 * @author louis
 * @date 2022/6/22
 * kafka 实现延时队列
 */
public class DelayQueue {

    @Autowired
    private KafkaConsumer<String, String> consumer;

    public void testDelayQueue() {
        String topic = "delay-minutes-1";

        List<String> strings = Collections.singletonList(topic);

        consumer.subscribe(strings);



    }
}
