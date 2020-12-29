package com.louis.dubbo.spi;

import com.louis.remote.api.Robots;

import java.util.Iterator;
import java.util.ServiceLoader;

/**
 * @author jun.liu
 * @since 2020/12/2 19:33
 */
public class Main {

    public static void main(String[] args) {
        ServiceLoader<Robots> serviceLoader = ServiceLoader.load(Robots.class);
        Iterator<Robots> iterator = serviceLoader.iterator();
        while (iterator.hasNext()) {
            Robots next = iterator.next();
            next.sayHello();

        }
    }
}
