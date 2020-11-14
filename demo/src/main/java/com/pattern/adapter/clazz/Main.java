package com.pattern.adapter.clazz;

/**
 * @author John·Louis
 * @date created on 2020/3/12
 * description:
 */
public class Main {

    public static void main(String[] args) {
        System.out.println("类适配器模式测试：");
        Target target = new ClassAdapter();
        target.request();
    }
}
