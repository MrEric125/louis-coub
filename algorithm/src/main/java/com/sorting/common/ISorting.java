package com.sorting.common;

/**
 * create by louis
 * <p> Date: 2018-12-04
 * <p> Version:1.0
 *
 * 排序基类
 */
public interface  ISorting<T extends Comparable<T>> {

     void sort(T[] a);
}
