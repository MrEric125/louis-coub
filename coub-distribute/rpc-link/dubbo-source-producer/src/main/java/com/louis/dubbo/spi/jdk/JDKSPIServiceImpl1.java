package com.louis.dubbo.spi.jdk;


import java.util.ServiceLoader;

/**
 * 首先spi机制是什么，他是怎么做的
 * <p>
 * dubbo中的spi是如何做的，我们能实现spi吗？有什么改进的地方，如何改进的，
 * <p>
 * spi对于项目的的意义，我们如何利用dubbo spi
 */
public class JDKSPIServiceImpl1 implements JDKSPIService {
    @Override
    public void printInfo() {
        System.out.println("Hello world");


    }

    public static void main(String[] args) {
        ServiceLoader<JDKSPIService> load = ServiceLoader.load(JDKSPIService.class);
        for (JDKSPIService printService : load) {
            printService.printInfo();
        }
    }
}
