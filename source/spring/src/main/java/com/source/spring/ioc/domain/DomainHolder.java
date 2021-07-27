package com.source.spring.ioc.domain;


import lombok.Data;
import org.springframework.beans.factory.config.BeanDefinitionHolder;
import org.springframework.context.annotation.Configuration;

/**
 * spring 源码中的实体
 * {@link org.springframework.beans.factory.config.BeanDefinition}
 * {@link BeanDefinitionHolder}
 *https://gitee.com/geektime-geekbang/geektime-spring-family
 *
 */
@Data
@Configuration
public class DomainHolder {


    private String name = "zhangsan";



}
