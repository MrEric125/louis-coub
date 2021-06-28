package com.source.spring.ioc;

import com.google.common.collect.Maps;
import com.source.spring.ioc.domain.DomainHolder;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Map;

public class DemoContainer {

    private Map<String, Object> maps = Maps.newConcurrentMap();

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.scan("com.source.spring.ioc");
        context.refresh();
        DomainHolder bean = context.getBean(DomainHolder.class);
        System.out.println(bean.getName());



    }
}
