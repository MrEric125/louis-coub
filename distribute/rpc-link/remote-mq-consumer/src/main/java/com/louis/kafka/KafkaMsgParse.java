package com.louis.kafka;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.louis.kafka.common.Constants;
import com.louis.kafka.common.MessageExt;
import org.apache.kafka.clients.consumer.ConsumerRecord;

public class KafkaMsgParse {
    public MessageExt<String,String> receiveParse(Object msgMate, Class<?> clazz) throws Exception {
        MessageExt<String,String> msgExt;
        String topic = null, key = null, body = null;
        if (clazz == ConsumerRecord.class) {
            ConsumerRecord<String, String> record = (ConsumerRecord<String, String>) msgMate;
            try {
                topic = record.topic();
                key = record.key() == null
                        ? null : record.key();
                body = record.value();

                long offset = record.offset();
                int partition = record.partition();
                MessageExt.MessageWrapper wrapper = JSON.parseObject(body, MessageExt.MessageWrapper.class);
                msgExt = new MessageExt<>(key, wrapper.getMessage(), topic, wrapper.getMessageId());
                msgExt.setHeaders(wrapper.getHeaders());
                msgExt.setOffset(offset);
                msgExt.setPartition(partition);
            } catch (JSONException je) {
//            LOGGER.warn("parse message failed! error: {}, body:{}", t.getMessage(), body);
                msgExt = new MessageExt<>(key, body, topic, null);
            }

            return msgExt;
        } else {
            return null;
        }


    }
}
