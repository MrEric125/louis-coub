package com.louis.longagocode.pattern.abstractfactory.example1;

import com.pattern.abstractfactory.example1.AbstractFactory;
import com.pattern.abstractfactory.example1.Product;

/**
 * @author John·Louis
 * @date created on 2020/3/9
 * description:
 */
public class Product2Factory extends AbstractFactory {
    @Override
    public Product newProduct() {
        return new ConcreteProduct2();
    }
}
