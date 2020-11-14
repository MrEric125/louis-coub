package com.pattern.flyweight;

/**
 * @author John·Louis
 * @date created on 2020/3/16
 * description:
 *  享元模式主要是解决可能会重复创建的大量工单的对象，我们常说的spring中的对象是单例的，
 *  但是spring中的对象的单例和单例设计模式是不一样的，单例设计模式是让
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
