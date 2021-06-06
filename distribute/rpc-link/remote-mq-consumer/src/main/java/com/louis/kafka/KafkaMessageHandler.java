package com.louis.kafka;

import com.louis.kafka.common.MessageExt;

import java.io.Serializable;

public interface KafkaMessageHandler<Key extends Serializable,Value extends Serializable> {

     void onMessage(MessageExt<Key,Value> messageExt) throws Exception;

}
