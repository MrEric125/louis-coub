package com.louis.minaProject.config;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

import java.util.Properties;

/**
 * @author jun.liu
 * @date created on 2020/10/30
 * description:
 */
@Configuration
@EnableConfigurationProperties(AcmeProperties.class)
public class MinaConfig {

    public PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        PropertySourcesPlaceholderConfigurer property = new PropertySourcesPlaceholderConfigurer();
        Properties properties = new Properties();
        property.setProperties(properties);
        return property;
    }
}
