package com.sorting.common;


import java.util.Arrays;
import java.util.Random;

/**
 * create by louis
 * <p> Date: 2018-12-04
 * <p> Version:1.0
 *
 * 排序辅助类
 */
public class SortUtils {

    /**
     * 显示当前数组
     * @param comparables
     */
    public static  void show(Comparable[] comparables) {
        Arrays.stream(comparables).forEach(System.out::println);
    }

    /**
     * compare which is less
     * @return
     */

    @SuppressWarnings("unchecked")
    public static  boolean less(Comparable v,Comparable w) {
        return v.compareTo(w) < 0;
    }

    /**
     * 交换两个位置
     * @param objects
     * @param i
     * @param j
     */
    public static  void exch(Comparable[] objects, int i, int j) {
        Comparable swap = objects[i];
        objects[i] = objects[j];
        objects[j] = swap;
    }

    /**
     * 查看从lo 位置到 hi位置是否是有序的
     * @param a
     * @param lo
     * @param hi
     * @return
     */
    public static  boolean isSort(Comparable[] a, int lo, int hi) {
        for (int i = lo + 1; i < hi; i++) {
            if (less(a[i],a[i-1])) {
                return false;
            }
        }
        return true;
    }

    /**
     * 判断整个数组是否是有序的
     * @param a
     * @return
     */
    public static  boolean isSort(Comparable[] a) {
        return isSort(a, 0, a.length);
    }

    /**
     * 判断从第h位到最后以为是否是有序的
     * @param a
     * @param h
     * @return
     */
    public static  boolean isHsort(Comparable[] a, int h) {
        for (int i = h ; i <a.length ; i++) {
            if (less(a[i], a[i - h])) {
                return false;
            }
        }
        return true;
    }



    /**
     * 将数组arr序列打乱
     * @param arr 传入的数组
     */
    public static  void shuffle(Comparable[] arr) {
        int n = arr.length;
        for (int i = 0; i < n; i++) {
            int v = (int) ((Math.random() * (n - i)) + i);
            Comparable swap = arr[i];
            arr[i] = arr[v];
            arr[v] = swap;
        }
    }

    /**
     * 生成一个数字数组数组
     * todo 后期可以写一个生成字符串的数组
     * @param n
     * @return
     */
    public  static Integer[] generateOrderArray(int n) {
        Integer[] arr = new Integer[n];
        for (int i = 0; i < n; i++) {
            arr[i] = i;
        }
        return arr;
    }

    public static Integer[] generateSameArray(int n) {
        Integer[] arr = new Integer[n];
        Random random = new Random();
        int i1 = random.nextInt(n);
        System.out.println(i1);
        for (int i = 0; i < n; i++) {
            arr[i] = i1;
        }
        return arr;
    }

}
