package com.luois.test;

import java.util.Arrays;

/**
 * @author louis
 * <p>
 * Date: 2019/11/1
 * Description:
 */
public class JVMTest {
    public static void main(String[] args) {
        Arrays.asList(args).forEach(System.out::println);
        long a = 4271011840L;
        double b = (a / 1024 )/ 1024;
        System.out.println(b);


    }
}
