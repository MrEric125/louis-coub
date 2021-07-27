package com.luois.genericTest;

import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 * @author jun.liu
 * @date created on 2020/8/2
 * description:
 */
public class App {

    public static void main(String[] args) {
        ArrayList<String> a = new ArrayList<String>();
        ArrayList<Integer> b = new ArrayList<Integer>();
        Type genericInterface = a.getClass().getGenericInterfaces()[0];
        Type superclass = a.getClass().getGenericSuperclass();
        Type anInterface = b.getClass().getGenericInterfaces()[0];
        System.out.println(genericInterface);

    }
}
