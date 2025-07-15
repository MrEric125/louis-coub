package com.louis.longagocode.pattern.adapter.obj;


import com.louis.longagocode.pattern.adapter.clazz.Adapter;
import com.louis.longagocode.pattern.adapter.clazz.Target;

/**
 * @author John·Louis
 * @date created on 2020/3/12
 * description:
 */
public class Main {

    public static void main(String[] args) {
        System.out.println("对象适配器模式测试：");
        Adapter adaptee = new Adapter();
        Target target = new ObjectAdapter(adaptee);
        target.request();
    }
}
