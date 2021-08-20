package com.source.spring.ioc;


import lombok.Data;
import org.springframework.beans.factory.annotation.Qualifier;

import javax.annotation.Resource;

@Data
//@Service
public class CycleDepService1 implements CycleDepService {

    @Resource
    @Qualifier
    private CycleDepService2 cycleDepService2;

    @Override
    public void run() {
        cycleDepService2.run();

    }
    public void service1() {
        System.out.println("service1");
    }
}
