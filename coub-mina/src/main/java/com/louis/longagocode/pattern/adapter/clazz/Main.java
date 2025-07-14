package com.louis.longagocode.pattern.adapter.clazz;

import com.pattern.adapter.clazz.ClassAdapter;
import com.pattern.adapter.clazz.Target;

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
