package com.pattern.memento;

import jdk.nashorn.internal.runtime.regexp.joni.constants.OPCode;

/**
 * @author John·Louis
 * @date created on 2020/2/15
 * description:
 */
public class OriginatorPrototype implements Cloneable{

    private String state;

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public OriginatorPrototype createMemento() {
        return this.clone();
    }

    public void restoreMemento(OriginatorPrototype opt) {
        this.setState(opt.getState());
    }

    public OriginatorPrototype clone() {
        try {
            return (OriginatorPrototype) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return null;
    }

}
