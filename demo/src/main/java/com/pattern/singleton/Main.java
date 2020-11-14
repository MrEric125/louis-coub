package com.pattern.singleton;

/**
 * @author John·Louis
 * @date created on 2020/3/12
 * description:
 */
public class Main {

    public static void main(String[] args) {
        System.out.println(Singleton.initMethod());
        System.out.println("调用实例化方法。。。。");
        Singleton.getInstance();

    }
}
