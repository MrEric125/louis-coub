package com.louis.kafka;

import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutionException;

/**
 * @author louis
 * <p>
 * Date: 2019/9/23
 * Description:
 */
@Service
public class MessageProducer {

    @Autowired
    KafkaProducer<String ,String> kafkaProducer;

    /**
     * 發送即忘
     * @param message
     */
    public void send(Message message) {
        ProducerRecord<String,String > record = new ProducerRecord<>(message.getTopic(), message.getMsg());
        kafkaProducer.send(record);
    }

    /**
     * 异步发送
     * @param message
     * @param callback
     */
    public void send(Message message,ProducerCallback callback){
        ProducerRecord<String,String > record = new ProducerRecord<>(message.getTopic(), message.getMsg());
        kafkaProducer.send(record, callback);

    }

    /**
     * 发送之后返回元数据
     * @param message
     * @return
     * @throws ExecutionException
     * @throws InterruptedException
     */
    public RecordMetadata sendAndGet(Message message) throws ExecutionException, InterruptedException {
        ProducerRecord<String,String > record = new ProducerRecord<>(message.getTopic(), message.getMsg());
        return   kafkaProducer.send(record).get();
    }


}
