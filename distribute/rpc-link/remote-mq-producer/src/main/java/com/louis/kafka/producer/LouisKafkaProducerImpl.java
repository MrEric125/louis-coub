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

    private volatile ConcurrentHashMap<String, KafkaProducer<Key,Value>> topicProducerMap = new ConcurrentHashMap<>();



    @Override
    public String send(String topic, Value message) throws Exception{
        Message<Key, Value> msg = new Message<>();
        msg.setTopic(topic);
        msg.setKey(null);
        msg.setValue(message);

        return super.send(msg);
    }

    @Override
    public String send(String topic, Value message, String partitionKey) throws Exception{

        Message<Key, Value> msg = new Message<>();
        msg.setTopic(topic);
        msg.setKey(null);
        msg.setValue(message);

        return super.send(msg);
    }

    @Override
    public String send(Message<Key, Value> message)  {
        try {
            return super.send(message);
        }catch (Exception e){
            e.printStackTrace();

        }
        return null;
    }

    @Override
    public String send(List<Message<Key, Value>> kafkaMessages) throws Exception{
        for (Message<Key, Value> kafkaMessage : kafkaMessages) {
            super.send(kafkaMessage);
        }
        return null;
    }

    @Override
    public String send(Message<Key, Value> message, Callback callback) throws Exception {
        return super.send(message);
    }
}
