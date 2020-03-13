package com.pattern.singleton;

/**
 * @author John·Louis
 * @date created on 2020/3/12
 * description:
 */
public class SingletonTest {
    private static SingletonTest ourInstance = null;

    public static SingletonTest getInstance() {
        System.out.println("静态方法 getInstance");
        if (ourInstance == null) {
            ourInstance = new SingletonTest();
        }
        return ourInstance;
    }

    public static String initMethod() {
        System.out.println("静态方法 initMethod");
        return "静态方法";

    }

    private SingletonTest() {
        System.out.println("实例化");

    }
}
