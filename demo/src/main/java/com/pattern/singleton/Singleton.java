package com.pattern.singleton;

/**
 * @author John·Louis
 * @date created on 2020/3/12
 * description:
 */
public class Singleton {
    private static Singleton ourInstance = null;

    public static Singleton getInstance() {
        System.out.println("静态方法 getInstance");
        if (ourInstance == null) {
            ourInstance = new Singleton();
        }
        return ourInstance;
    }

    public static String initMethod() {
        System.out.println("静态方法 initMethod");
        return "静态方法";

    }

    private Singleton() {
        System.out.println("实例化");

    }
}
