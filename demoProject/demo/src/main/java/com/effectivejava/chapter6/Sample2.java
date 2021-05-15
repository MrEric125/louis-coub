package com.effectivejava.chapter6;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Sample2 {



    @ExceptionTest(NullPointerException.class)
    public static void m1() {
        int i = 0;
        i = i / i;

    }

    public static void main(String[] args) {
        int passed=0;
        Class<Sample2> testClass = Sample2.class;
        for (Method method : testClass.getDeclaredMethods()) {
            if (method.isAnnotationPresent(ExceptionTest.class)) {
                try {
                    method.invoke(null);
                } catch (InvocationTargetException e) {
                    Throwable thorwable = e.getCause();
                    Class<? extends Throwable> value = method.getAnnotation(ExceptionTest.class).value();
                    if (value.isInstance(thorwable)) {
                        passed++;
                        System.out.println(passed);
                    } else {
                        System.out.println("test failed");
                    }
                } catch (Exception e) {
                    System.out.println(e.getMessage());

                }
            }
        }
    }
}
