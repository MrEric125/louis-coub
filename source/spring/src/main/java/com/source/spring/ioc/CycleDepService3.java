package com.source.spring.ioc;

import lombok.Data;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Data
//@Service
public class CycleDepService3 implements CycleDepService {
    @Resource
    private CycleDepService1 cycleDepService1;

//    public CycleDepService3(CycleDepService1 cycleDepService1) {
//        this.cycleDepService1 = cycleDepService1;
//    }

    @Override
    public void run() {
        System.out.println("run.........");

        cycleDepService1.service1();
    }
}
