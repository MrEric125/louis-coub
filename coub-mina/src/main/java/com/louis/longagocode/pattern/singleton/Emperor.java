package com.louis.longagocode.pattern.singleton;

/**
 * @author jun.liu
 * @date created on 2020/7/19
 * description:
 */
public class Emperor {
    /**
     * 第一种实例
     */
    private static Emperor ourInstance = new Emperor();

    private static final Emperor instance2 = new Emperor();

    public static Emperor getInstance() {
//        return ourInstance;
        return ourInstance;

    }

    public static void say() {
        System.out.println("吾乃开国皇帝");
    }

    private Emperor() {
    }
}
