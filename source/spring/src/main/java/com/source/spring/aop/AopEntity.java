package com.source.spring.aop;

import lombok.Data;

/**
 * @author jun.liu
 * @date created on 2020/9/4
 * description:
 */
@Data
public class AopEntity implements Cloneable{

    private String name;

    private Long id;

    private String title;


    public  void  test() {
        System.out.println("test");
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return this;
    }
}
