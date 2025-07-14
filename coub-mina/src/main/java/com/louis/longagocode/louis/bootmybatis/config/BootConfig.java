package com.louis.longagocode.louis.bootmybatis.config;

import com.louis.bootmybatis.config.BootLouisProperties;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * @author louis
 * <p>
 * Date: 2019/8/1
 * Description:
 */
@ConditionalOnProperty(prefix = "louis.config",name = "app-config",havingValue = "zhangsan")
@Configuration
public class BootConfig {


    @Bean
    public BootLouisProperties bootLouisProperties() {
        return new BootLouisProperties();
    }
}


