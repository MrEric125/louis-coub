package com.source.spring.ioc;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;

@Data
public class CycleDepService3 implements CycleDepService {
    @Autowired
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
