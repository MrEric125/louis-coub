package com.pattern.mediator;

import org.assertj.core.util.Lists;

import java.util.List;

/**
 * @author John·Louis
 * @date created on 2020/2/15
 * description:
 */
public class ConcreteMediator extends Mediator {

    private List<Colleague> colleagues = Lists.newArrayList();

    @Override
    public void register(Colleague colleague) {
        colleagues.add(colleague);
        colleague.setMedium(this);
    }

    @Override
    public void relay(Colleague colleague) {

        for (Colleague colleague1 : colleagues) {
            if (!colleague1.equals(colleague)) {
                colleague1.receive();
            }
        }
    }
}
