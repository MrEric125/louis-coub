package com.louis.longagocode.pattern.flyweight;

/**
 * @author John·Louis
 * @date created on 2020/3/16
 * description:
 */
public class UnSharedConcreteFlyweight {

    private String info;

    public UnSharedConcreteFlyweight(String info) {
        this.info = info;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}
