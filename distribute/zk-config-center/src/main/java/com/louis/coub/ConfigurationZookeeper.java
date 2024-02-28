package com.louis.coub;

import org.springframework.cloud.zookeeper.ConditionalOnZookeeperEnabled;
import org.springframework.context.annotation.Configuration;

@ConditionalOnZookeeperEnabled
@Configuration
public class ConfigurationZookeeper {

//    @Bean
//    @ConditionalOnMissingBean
//    public ZookeeperConfigProperties zookeeperConfigProperties(Environment env) {
//        ZookeeperConfigProperties properties =
//        if (!StringUtils.hasLength(properties.getName())) {
//            properties.setName(env.getProperty("spring.application.name", "application"));
//        }
//
//        return properties;
//    }
}
