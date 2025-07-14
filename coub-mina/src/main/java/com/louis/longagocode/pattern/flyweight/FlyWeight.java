package com.louis.longagocode.pattern.flyweight;

import com.pattern.flyweight.UnSharedConcreteFlyweight;

/**
 * @author John·Louis
 * @date created on 2020/3/16
 * description:
 */
public interface FlyWeight {

    public void operation(UnSharedConcreteFlyweight state);

}
