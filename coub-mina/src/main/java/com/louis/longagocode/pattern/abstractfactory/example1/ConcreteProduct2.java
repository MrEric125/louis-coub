package com.louis.longagocode.pattern.abstractfactory.example1;

import com.pattern.abstractfactory.example1.Product;

/**
 * @author John·Louis
 * @date created on 2020/3/9
 * description:
 */
public class ConcreteProduct2 implements Product {
    @Override
    public void show() {
        System.out.println("product");
    }
}
