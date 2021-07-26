package com.source.spring.aop;

public class HandlerTest {

    public static void main(String[] args) {
        EntityService entityService = new EntityServiceImpl();

        MyInvocationHandler invocationHandler = new MyInvocationHandler(entityService);

        //todo 这里其实有问题，获取代理的时候，会频繁调用invoke中的方法，有多少个断点就会打印多少次
        EntityService proxy= (EntityService) invocationHandler.getProxy();

        proxy.test();

    }
}
