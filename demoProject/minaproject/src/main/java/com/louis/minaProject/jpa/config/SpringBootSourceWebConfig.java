package com.louis.minaProject.jpa.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @author jun.liu
 * @date created on 2020/11/2
 * description:
 */
@Configuration
public class SpringBootSourceWebConfig {

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
