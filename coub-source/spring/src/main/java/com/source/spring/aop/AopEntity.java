package com.source.spring.aop;

import lombok.Data;

/**
 * @author jun.liu
 * @date created on 2020/9/4
 * description:
 */
@Data
public class AopEntity implements Cloneable {

    private String name = "default";

    private Long id = 0L;

    private String title = "default";


    public void test() {
        System.out.println("test");
    }

    public void noInvoke() {
        System.out.println("noInvoke");
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return this;
    }
}
