package com.louis.kafka.client.entity;

import lombok.Getter;
import lombok.Setter;

/**
 * @author louis
 * @date 2022/9/7
 */

public class DynamicEvent {

    private String topic;

    private String dynamicTopic;

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getDynamicTopic() {
        return dynamicTopic;
    }

    public void setDynamicTopic(String dynamicTopic) {
        this.dynamicTopic = dynamicTopic;
    }
}
