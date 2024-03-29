package com.louis.kafka.common;

public enum MQType {
    ROCKETMQ, KAFKA;

    public static MQType convert(String mqType) {
        return MQType.valueOf(mqType.toUpperCase());
    }
}