package com.concurrent;

/**
 * @author louis
 * <p>
 * Date: 2019/8/20
 * Description:
 */
public interface MyInterface {

    default void remove(){
        System.out.println("hhhh");
    }
}
