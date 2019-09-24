package com.java8;

/**
 * @author louis
 * <p>
 * Date: 2019/9/24
 * Description:
 */
public interface Builder {

    void add(Integer name, String supplier);

    default void delete(Integer name){
        System.out.println(name);
    }
}