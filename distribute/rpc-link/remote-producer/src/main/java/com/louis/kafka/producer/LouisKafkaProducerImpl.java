package com.louis.kafka.producer;

import com.louis.kafka.common.Message;
import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author John·Louis
 * @date create in 2019/9/21
 * description:
 */

public class LouisKafkaProducerImpl<Key extends Serializable,Value extends Serializable> extends BaseKafkaProducer<Key,Value> implements IKafkaProducer<Key,Value> {

    private volatile ConcurrentHashMap<String, KafkaProducer> topicProducerMap = new ConcurrentHashMap<>();



    @Override
    public String send(String topic, Value message) {
        Message<Key, Value> msg = new Message<>();
        msg.setTopic(topic);
        msg.setKey(null);
        msg.setValue(message);

        return super.send(msg);
    }

    @Override
    public String send(String topic, Value message, String partitionKey) {
        Message<Key, Value> msg = new Message<>();
        msg.setTopic(topic);
        msg.setKey(null);
        msg.setValue(message);

        return super.send(msg);
    }

    @Override
    public String send(Message<Key, Value> message) {
        return super.send(message);
    }

    @Override
    public String send(List<Message<Key, Value>> kafkaMessages) {
        for (Message<Key, Value> kafkaMessage : kafkaMessages) {
            super.send(kafkaMessage);
        }
        return null;
    }

    @Override
    public String send(Message<Key, Value> message, Callback callback) {
        return super.send(message);
    }
}
