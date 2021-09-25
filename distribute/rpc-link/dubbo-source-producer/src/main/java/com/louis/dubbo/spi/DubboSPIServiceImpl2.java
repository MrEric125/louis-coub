package com.louis.dubbo.spi;

import org.apache.dubbo.common.extension.Adaptive;

@Adaptive()
public class DubboSPIServiceImpl2 implements DubboSPIService{
    @Override
    public void printInfo() {
        System.out.println("dubbo spi 2");
    }
}
