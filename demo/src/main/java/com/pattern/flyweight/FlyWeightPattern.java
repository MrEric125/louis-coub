package com.pattern.flyweight;

/**
 * @author John·Louis
 * @date created on 2020/3/16
 * description:
 */
public class FlyWeightPattern {

    public static void main(String[] args) {
        FlyWeightFactory factory = new FlyWeightFactory();
        FlyWeight f0 = factory.getFlyWeight("a");
        FlyWeight f1 = factory.getFlyWeight("a");
        FlyWeight f2 = factory.getFlyWeight("a");
        FlyWeight f3 = factory.getFlyWeight("c");
        FlyWeight f4 = factory.getFlyWeight("b");
        FlyWeight f5 = factory.getFlyWeight("b");
        f0.operation(new UnSharedConcreteFlyweight("第1次调用a。"));
        f1.operation(new UnSharedConcreteFlyweight("第2次调用a。"));
        f2.operation(new UnSharedConcreteFlyweight("第3次调用a。"));
        f3.operation(new UnSharedConcreteFlyweight("第1次调用b。"));
        f4.operation(new UnSharedConcreteFlyweight("第2次调用b。"));

    }
}
