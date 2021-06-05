package com.louis.kafka;

import com.louis.kafka.common.MessageExt;

public interface KafkaMessageHandler {

    public void onMessage(MessageExt messageExt);

}
