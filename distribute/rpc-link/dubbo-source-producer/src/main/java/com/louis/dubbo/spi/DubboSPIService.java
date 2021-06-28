package com.louis.dubbo.spi;


import org.apache.dubbo.common.extension.SPI;

@SPI("impl")
public interface DubboSPIService {

    void printInfo();
}
