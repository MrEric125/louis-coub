package com.louis.longagocode.pattern.mediator;

/**
 * @author John·Louis
 * @date created on 2020/2/15
 * description:
 */
public class ConcreteColleague2 extends Colleague {
    @Override
    public void send() {

        System.out.println("具体同事类2发出请求。");
        mediator.relay(this); //请中介者转发

    }

    @Override
    public void receive() {
        System.out.println("具体同事类2收到请求。");

    }
}
