package com.louis.kafka;

import com.alibaba.fastjson.JSONException;
import com.louis.kafka.common.MessageExt;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;

import java.io.Serializable;


@Slf4j
public class KafkaMsgParse<Key extends Serializable,Value extends Serializable> {
    @SuppressWarnings("unchecked")
    public MessageExt<Key,Value> receiveParse(Object msgMate, Class<?> clazz)  {
        MessageExt<Key,Value> msgExt;
        String topic = null;
        Key key = null;
        Value body = null;
        if (clazz == ConsumerRecord.class) {
            ConsumerRecord<Key, Value> record = (ConsumerRecord<Key, Value>) msgMate;
            try {
                topic = record.topic();
                key = record.key();
                body = record.value();

                long offset = record.offset();
                int partition = record.partition();
                msgExt = new MessageExt<>(key, body, topic, null);
                msgExt.setOffset(offset);
                msgExt.setPartition(partition);
            } catch (JSONException je) {
            log.warn("parse message failed! error: {}, body:{}", je.getMessage(), body);
                msgExt = new MessageExt<>(key, body, topic, null);
            }

            return msgExt;
        } else {
            log.error("当前消息转换不支持，请对接文档或联系研发");

            return null;
        }


    }
}
