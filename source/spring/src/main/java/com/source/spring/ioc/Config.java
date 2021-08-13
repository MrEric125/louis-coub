package com.source.spring.ioc;

import com.source.spring.ioc.domain.DomainHolder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {

    @Bean
    public DomainHolder domainHolder() {
        return new DomainHolder();
    }
}
