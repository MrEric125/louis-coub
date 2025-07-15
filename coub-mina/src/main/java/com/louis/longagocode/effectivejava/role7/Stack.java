package com.louis.longagocode.effectivejava.role7;

import java.util.Arrays;
import java.util.EmptyStackException;

/**
 * @author louis
 * <p>
 * Date: 2020/1/3
 * Description:
 * <p>
 * 导致过期对象不会被回收
 */
public class Stack {
    private Object[] elements;

    private int size = 0;

    private static final int DEFAULT_INITIAL_CAPACITY = 16;

    public Stack() {
        elements = new Object[DEFAULT_INITIAL_CAPACITY];
    }

    public void push(Object object) {
        ensureCapacity();
        elements[size++] = object;

    }

    //    如果一个对象的引用被无意识的保留起来了，那么垃圾回收机制不仅不会回收这个对象
//    还不会处理这个对象所应用的其他对象
    public Object pop() {
        if (size == 0) {
            throw new EmptyStackException();
        }
        return elements[--size];
    }

    /**
     * 修复版
     *
     * @return
     */
    public Object popOk() {
        if (size == 0) {
            throw new EmptyStackException();
        }
        Object result = elements[--size];
        elements[size] = null;
        return result;
    }

    private void ensureCapacity() {
        if (elements.length == size) {
            elements = Arrays.copyOf(elements, 2 * size + 1);
        }
    }
}
