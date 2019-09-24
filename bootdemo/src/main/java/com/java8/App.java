package com.java8;

/**
 * @author louis
 * <p>
 * Date: 2019/9/24
 * Description:
 */
public class App {
    public static void main(String[] args) {
        BuilderFactory factory = BuilderFactory.factory(builder -> {
            builder.add(1, "zhangsan");
            builder.add(2, "lisi");
        });
        String s = factory.create(1);
        System.out.println(s);
    }
}
