package com.louis.longagocode.pattern.abstractfactory.example1;


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
