package com.web.listener;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * @author John·Louis
 * @date created on 2020/2/13
 * description:
 */

public class People {

    @Setter
    @Getter
    private String name;

    private MyListener myListener;

    public void eat() {
        System.out.println("吃饭");
        myListener.doEat(new Event(this));
    }
    public void sleep() {
        System.out.println("睡觉");
        myListener.doSleep(new Event(this));

    }

    public People(String name, MyListener myListener) {
        this.name = name;
        this.myListener = myListener;
    }

    public People(String name) {
        this.name = name;
    }

    public void registerMyListener(MyListener myListener) {
        this.myListener = myListener;
    }
}
