package com.louis.kafka.producer;

import com.louis.kafka.common.Message;
import org.apache.kafka.clients.producer.Callback;

import java.io.Serializable;
import java.util.List;

public interface IKafkaProducer<K extends Serializable, V extends Serializable> {

    String send(String topic, V message) throws Exception;

    String send(String topic, V message, String partitionKey) throws Exception;;

    String send(Message<K, V> message) throws Exception;;

    String send(List<Message<K, V>> kafkaMessages) throws Exception;;

    String send(Message<K, V> message, Callback callback) throws Exception;;
}
