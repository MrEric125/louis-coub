package com.louis.bootmybatis.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 *这个里面我在配置文件中shiyong@Bean的方式
 */
@Data
@ConfigurationProperties(prefix =BootLouisProperties.PREFIX)
public class BootLouisProperties {

    static final String PREFIX = "boot.louis";

    private String boot;

    private String louis;

    private long time;

    private int port;


}
