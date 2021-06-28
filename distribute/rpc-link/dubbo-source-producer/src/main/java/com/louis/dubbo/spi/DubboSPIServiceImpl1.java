package com.louis.dubbo.spi;

import org.apache.dubbo.common.extension.ExtensionLoader;

public class DubboSPIServiceImpl1 implements DubboSPIService{
    @Override
    public void printInfo() {
        System.out.println("dubbo spi");
    }

    public static void main(String[] args) {
        DubboSPIService extension = ExtensionLoader.getExtensionLoader(DubboSPIService.class).getDefaultExtension();

        extension.printInfo();

    }
}
