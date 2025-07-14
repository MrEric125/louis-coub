package com.louis.longagocode.pattern.flyweight;

import java.util.HashMap;
import java.util.Map;

/**
 * @author John·Louis
 * @date created on 2020/3/16
 * description:
 */
public class FlyWeightFactory {

    private Map<String, FlyWeight> flyWeights = new HashMap<>();

    public FlyWeight getFlyWeight(String key) {
        FlyWeight flyWeight = flyWeights.get(key);
        if (flyWeight != null) {
            System.out.println("具体享元" + key + "已经存在，被成功获取！");
        } else {

            flyWeight = new ConcreteFlyWeight(key);
            flyWeights.put(key, flyWeight);
        }
        return flyWeight;
    }


}
