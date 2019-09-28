package com.louis.kafka;

import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public void send(Message message) {
        ProducerRecord<String,String > record = new ProducerRecord<>(message.getTopic(), message.getMsg());
        kafkaProducer.send(record);
    }
    public void send(Message message,Callback callback){
        ProducerRecord<String,String > record = new ProducerRecord<>(message.getTopic(), message.getMsg());
        kafkaProducer.send(record, callback);

    }


}
