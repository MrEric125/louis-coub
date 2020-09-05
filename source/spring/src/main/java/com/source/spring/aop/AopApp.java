package com.source.spring.aop;

import com.alibaba.fastjson.JSON;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author jun.liu
 * @date created on 2020/9/4
 * description:
 */
public class AopApp {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(AopAspect.class);
        context.scan("com.source.spring.aop");

        context.refresh();

        AopEntity aopEntity = context.getBean("aopEntity", AopEntity.class);
        aopEntity.test();
        System.out.println(JSON.toJSONString(aopEntity, true));
    }

}
