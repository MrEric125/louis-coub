package com.louis.kafka.common;

public enum MQVersion {
    K_V_0_8_2, K_V_2_0_0, K_V_2_3_0, R_V_4_2_0;


    public static MQVersion convert(String mqVersion) {
        return MQVersion.valueOf(mqVersion.toUpperCase());
    }
}