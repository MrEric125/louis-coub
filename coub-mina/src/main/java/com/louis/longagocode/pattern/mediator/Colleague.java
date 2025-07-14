package com.louis.longagocode.pattern.mediator;

/**
 * @author John·Louis
 * @date created on 2020/2/15
 * description:
 */
abstract class Colleague {

    protected Mediator mediator;

    public abstract void send();

    public void setMedium(Mediator mediator) {
        this.mediator = mediator;
    }

    public abstract void receive();
}
