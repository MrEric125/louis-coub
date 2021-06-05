package com.louis.kafka.common;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AuthInfo {
    private String secretKey;
    private String serverAddr;
    private int authTimeout;
}
