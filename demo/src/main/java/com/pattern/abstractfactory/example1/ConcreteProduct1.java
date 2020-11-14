package com.pattern.abstractfactory.example1;

/**
 * @author John·Louis
 * @date created on 2020/3/9
 * description:
 */
public class ConcreteProduct1 implements Product {

    @Override
    public void show() {
        System.out.println("product 1");
    }
}
