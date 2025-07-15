package com.source.spring.ioc;


import jakarta.annotation.Resource;
import lombok.Data;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Data
@Service
public class CycleDepService1 implements CycleDepService {

    @Resource
    @Qualifier
    private CycleDepService3 cycleDepService3;

    @Override
    public void run() {
        cycleDepService3.run();

    }

    public void service1() {
        System.out.println("service1");
    }
}
