package com.louis.dubbo.consumer;

import com.louis.remote.api.Robots;

/**
 * @author jun.liu
 * @date created on 2020/8/12
 * description:
 */
public class Bumblebee implements Robots {
    @Override
    public void sayHello() {
        System.out.println(" Bumblebee robots");
    }
}
