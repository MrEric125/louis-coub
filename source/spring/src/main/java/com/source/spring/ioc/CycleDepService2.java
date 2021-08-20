package com.source.spring.ioc;

import lombok.Data;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Data
//@Service
public class CycleDepService2 implements CycleDepService {

    @Resource
    private CycleDepService3 cycleDepService3;

//    public CycleDepService2(CycleDepService3 cycleDepService3) {
//        this.cycleDepService3 = cycleDepService3;
//    }

    @Override
    public void run() {
        cycleDepService3.run();

    }
}
