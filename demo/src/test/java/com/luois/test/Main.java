package com.luois.test;

import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Method;

/**
 * @author John·Louis
 * @date created on 2020/2/11
 * description:
 */
public class Main {

    public static void main(String[] args){



        Parent parent = new Parent();


        Object privateMethod = ReflectUtils.invoke(parent, "privateMethod", new Class[]{String.class}, "outPrint");
        System.out.println((String) privateMethod);

        Method method = ReflectionUtils.findMethod(parent.getClass(), "privateMethod", String.class);

        if (method != null) {
            ReflectionUtils.makeAccessible(method);

            Object o = ReflectionUtils.invokeMethod(method, parent, "outPrint");

            System.out.println(o);
        }


    }
}
