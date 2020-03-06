package com.concurrent.unsafe;

import sun.misc.Unsafe;

import java.lang.reflect.Field;
import java.util.function.Consumer;

/**
 * @author John·Louis
 * @date created on 2020/2/20
 * description:
 */
public class UnsafeTest {

    private static Unsafe reflectGetUnsafe() {
        try {
            Field unsafe = Unsafe.class.getDeclaredField("theUnsafe");
            unsafe.setAccessible(true);
            return (Unsafe) unsafe.get(null);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void functionTest() {
        Consumer<String> consumer = s -> System.out.printf(s);
        consumer.accept("lambda");

    }
}
