package com.pattern.mediator;

/**
 * @author John·Louis
 * @date created on 2020/2/15
 * description:
 */
public class ConcreteColleague1 extends Colleague {
    @Override
    public void send() {
        System.out.println("具体同事类1发出请求。");
        mediator.relay(this); //请中介者转发
    }

    @Override
    public void receive() {

        System.out.println("具体同事类1收到请求。");
    }
}
