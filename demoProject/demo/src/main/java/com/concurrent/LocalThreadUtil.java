package com.concurrent;

public class LocalThreadUtil {

    private static ThreadLocal<String> threadLocal = new ThreadLocal<>();
    private static InheritableThreadLocal<String> inheritableThreadLocal = new InheritableThreadLocal<>();


    public static void main(String[] args) {
        threadLocal.set("mian threadLocal");
        inheritableThreadLocal.set("mian inheritableThreadLocal");

        new Thread(() -> {
            System.out.println("threadLocal is data is =====" + threadLocal.get());
            System.out.println("inheritableThreadLocal is data is =====" + inheritableThreadLocal.get());
        }).start();
    }
}
