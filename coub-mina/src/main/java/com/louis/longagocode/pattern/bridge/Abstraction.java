package com.louis.longagocode.pattern.bridge;

import com.pattern.bridge.Implementor;

/**
 * @author John·Louis
 * @date created on 2020/3/12
 * description:
 */
public abstract class Abstraction {
    protected Implementor implementor;

    public Abstraction(Implementor implementor) {
        this.implementor = implementor;
    }

    public abstract void operation();

}
