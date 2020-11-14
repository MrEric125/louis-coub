package com.source.spring.aop;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * @author jun.liu
 * @date created on 2020/9/4
 * description:
 */
@EnableAspectJAutoProxy
@Configuration
public class AnnotationConfig {

    @Bean
    public AopEntity aopEntity() {
        AopEntity entity = new AopEntity();
        entity.setId(1212L);
        entity.setName("zhangsan");
        entity.setTitle("title");
        return entity;
    }


}
