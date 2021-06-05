package com.louis.kafka;

import com.louis.kafka.common.Message;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.concurrent.ExecutionException;

/**
 * @author louis
 * <p>
 * Date: 2019/9/23
 * Description:
 * 目前不支持批量传输不同topic的数据，批量传输只能传输相同topic的数据
 */
@Deprecated
@Service
public class MessageProducer<Key extends Serializable, V extends Serializable> {

    @Autowired
    private KafkaProducer<Key ,V> kafkaProducer;

    /**
     * 發送即忘
     *
     * @param message 消息主题
     */
    public void send(com.louis.kafka.common.Message<Key, V> message) {
        ProducerRecord<Key, V> record = new ProducerRecord<>(message.getTopic(), message.getKey(), message.getValue());
        kafkaProducer.send(record);
    }

    /**
     * 异步发送
     * @param message
     * @param callback
     */
    public void send(com.louis.kafka.common.Message<Key,V> message, ProducerCallback callback){
        ProducerRecord<Key, V> record = new ProducerRecord<>(message.getTopic(), message.getKey(), message.getValue());
        kafkaProducer.send(record, callback);

    }

    /**
     * 发送之后返回元数据
     * @param message
     * @return
     * @throws ExecutionException
     * @throws InterruptedException
     */
    public RecordMetadata sendAndGet(Message<Key,V> message) throws ExecutionException, InterruptedException {
        ProducerRecord<Key,V> record = new ProducerRecord<>(message.getTopic(),message.getKey(), message.getValue());
        return   kafkaProducer.send(record).get();
    }

}
