package com.louis.longagocode.pattern.flyweight;


/**
 * @author John·Louis
 * @date created on 2020/3/16
 * description:
 */
public class ConcreteFlyWeight implements FlyWeight {

    private String key;

    public ConcreteFlyWeight(String key) {
        this.key = key;
        System.out.println("具体享元" + key + "被创建！");
    }

    @Override
    public void operation(UnSharedConcreteFlyweight state) {
        System.out.print("具体享元" + key + "被调用，");
        System.out.println("非享元信息是:" + state.getInfo());


    }
}
