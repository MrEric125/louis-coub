package com.louis.kafka.producer;

import com.louis.kafka.common.Message;
import org.apache.kafka.clients.producer.Callback;

import java.io.Serializable;
import java.util.List;

public interface IKafkaProducer<K extends Serializable, V extends Serializable> {

    String send(String topic, V message);

    String send(String topic, V message, String partitionKey);

    String send(Message<K, V> message);

    String send(List<Message<K, V>> kafkaMessages);

    String send(Message<K, V> message, Callback callback);
}
