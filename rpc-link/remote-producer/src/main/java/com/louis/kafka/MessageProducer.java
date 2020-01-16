package com.louis.kafka;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * @author louis
 * <p>
 * Date: 2019/9/23
 * Description:
 * 目前不支持批量传输不同topic的数据，批量传输只能传输相同topic的数据
 */
@Service
public class MessageProducer<ID extends Serializable, V extends Serializable> {

    @Autowired
    private KafkaProducer<ID ,V> kafkaProducer;

    /**
     * 發送即忘
     *
     * @param message 消息主题
     */
    public void send(Message<ID, V> message) {
        ProducerRecord<ID, V> record = new ProducerRecord<>(message.getTopic(), message.getId(), message.getMsg());
        kafkaProducer.send(record);
    }

    /**
     * 异步发送
     * @param message
     * @param callback
     */
    public void send(Message<ID,V> message,ProducerCallback callback){
        ProducerRecord<ID, V> record = new ProducerRecord<>(message.getTopic(), message.getId(), message.getMsg());
        kafkaProducer.send(record, callback);

    }

    /**
     * 发送之后返回元数据
     * @param message
     * @return
     * @throws ExecutionException
     * @throws InterruptedException
     */
    public RecordMetadata sendAndGet(Message<ID,V> message) throws ExecutionException, InterruptedException {
        ProducerRecord<ID,V> record = new ProducerRecord<>(message.getTopic(),message.getId(), message.getMsg());
        return   kafkaProducer.send(record).get();
    }

}
