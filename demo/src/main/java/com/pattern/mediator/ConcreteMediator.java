package com.pattern.mediator;

import org.assertj.core.util.Lists;

import java.util.List;

/**
 * @author John·Louis
 * @date created on 2020/2/15
 * description:
 */
public class ConcreteMediator implements Mediator {

    private List<Colleague> colleagues = Lists.newArrayList();

    @Override
    public void register(final Colleague colleague) {
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

        for (int i = 0; i < colleagues.size(); i++) {
            Colleague colleague1 = colleagues.get(i);
            colleague1.receive();

        }
        colleagues.stream().forEach(System.out::println);
    }
}
