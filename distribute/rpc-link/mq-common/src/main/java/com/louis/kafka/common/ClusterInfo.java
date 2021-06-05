package com.louis.kafka.common;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ClusterInfo {
    private Long id;


    private String zoneCode;
    private String flowGroupCode;
    private String physicalDcCode;
    private MQType mqType;
    private MQVersion mqVersion;

    private String clusterCode;
    private String brokers;
    private String zkConns;
}
