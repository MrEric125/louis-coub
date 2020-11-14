package com.luois.test;

/**
 * @author John·Louis
 * @date created on 2020/3/22
 * description:
 */
public class StaticTest {

    static {
        System.out.println(" block of static");
    }
    public StaticTest() {
        System.out.println("construct");
    }

    public static void method1() {
        System.out.println("method1");
    }

    public static void method2() {
        System.out.println("method2");
    }



    private static StaticTest instance = new StaticTest();
}
