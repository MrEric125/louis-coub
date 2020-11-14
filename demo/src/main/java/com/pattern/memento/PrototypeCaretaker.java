package com.pattern.memento;

/**
 * @author John·Louis
 * @date created on 2020/2/15
 * description:
 */
public class PrototypeCaretaker {

    private OriginatorPrototype opt;

    public void setMemento(OriginatorPrototype opt) {
        this.opt = opt;
    }
    public OriginatorPrototype  getMemento() {
        return opt;
    }
}
