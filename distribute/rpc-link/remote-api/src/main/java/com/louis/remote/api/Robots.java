package com.louis.remote.api;

import org.apache.dubbo.common.extension.SPI;

/**
 * @author jun.liu
 * @date created on 2020/8/12
 * description:
 */
@SPI()
public interface Robots {

    public void sayHello();
}
