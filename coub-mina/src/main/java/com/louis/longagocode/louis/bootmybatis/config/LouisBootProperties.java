package com.louis.longagocode.louis.bootmybatis.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Service;

/**
 * @author louis
 * <p>
 * Date: 2019/8/1
 * Description:
 */
@Data
@Service
@ConfigurationProperties(prefix =LouisBootProperties.PREFIX)
public class LouisBootProperties {

    static final String PREFIX = "louis.boot";

    private int bootTime;

    private String appName;

    private int port;

    public int getBootTime() {
        return bootTime;
    }

}
