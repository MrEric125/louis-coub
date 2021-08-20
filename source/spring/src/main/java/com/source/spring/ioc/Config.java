package com.source.spring.ioc;

import com.source.spring.ioc.domain.DomainHolder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
@Configuration
public class Config {

    @Bean
    public DomainHolder domainHolder() {
        return new DomainHolder();
    }

    @Bean
    public CycleDepService1 cycleDepService1() {
        CycleDepService1 cycleDepService1 = new CycleDepService1();
//        cycleDepService1.setCycleDepService2(cycleDepService2);
        System.out.println("创建 service1");
        return cycleDepService1;
    }

    @Bean
    public CycleDepService2 cycleDepService2() {
        CycleDepService2 cycleDepService2 = new CycleDepService2();
//        cycleDepService2.setCycleDepService3(cycleDepService3);
        System.out.println("创建 service2");
        return cycleDepService2;
    }

    @Bean
    public CycleDepService3 cycleDepService3() {
        CycleDepService3 cycleDepService3 = new CycleDepService3();
//        cycleDepService3.setCycleDepService1(cycleDepService1);
        System.out.println("创建 service3");

        return cycleDepService3;
    }
}
