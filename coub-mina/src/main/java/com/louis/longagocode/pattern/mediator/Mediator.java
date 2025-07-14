package com.louis.longagocode.pattern.mediator;

import com.pattern.mediator.Colleague;

/**
 * @author John·Louis
 * @date created on 2020/2/15
 * description:
 */
public interface Mediator {


    public abstract void register(Colleague colleague);

    public abstract void relay(Colleague colleague);
}
