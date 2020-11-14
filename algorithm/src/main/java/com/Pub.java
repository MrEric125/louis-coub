package com;

/**
 * @author louis
 * @Date 2020/1/14
 * description:
 */
public class Pub {
    static {
        System.out.println("static pub");
    }

    public Pub() {
        System.out.println("construct pub");
    }
    public static void tt() {
        System.out.println("static method pub");

    }
}
