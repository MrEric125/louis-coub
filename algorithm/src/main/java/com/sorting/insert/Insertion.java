package com.sorting.insert;

import com.sorting.common.ISorting;
import com.sorting.common.SortUtils;

import java.util.Arrays;


/**
 * create by louis
 * <p> Date: 2018-12-04
 * <p> Version:1.0
 * 插入排序
 *  默认数组的最前面是有序的，将后面的元素依次插入到这个数组的相应位置
 *  时间复杂度 O(n2)
 *  空间复杂度 O(1)
 *  可以通过二分插入排序这种方式来优化
 *  这是一种最常规的插入排序
 */
public class Insertion<T extends Comparable<T>> implements ISorting<T> {


    @Override
    public void sort(T[] a) {
        int n = a.length;
        for (int i = 0; i < n; i++) {
            for (int j = i; j > 0 && SortUtils.less(a[j], a[j - 1]); j--) {
                //后面一个元素比前面一个元素小的时候才回执行
                SortUtils.exch(a, j, j - 1);
            }
            assert SortUtils.isSort(a, 0, i);
        }
        assert SortUtils.isSort(a);
    }

    public void sort(T[] a, int lo, int hi) {
        for (int i = lo; i <=hi ; i++) {
//            写法一
//            for (int j = i; j > lo && SortUtils.less(a[j], a[j - 1]); j--) {
//                SortUtils.exch(a, j, j - 1);
//            }
//            写法二
            for (int j = i; j > 0; j--) {
                if (SortUtils.less(a[j], a[j - 1])) {
                    SortUtils.exch(a, j, j - 1);
                }
            }
//            写法三
//            T e = a[i];
//            int j = i;
//            for (; j > 0 && a[j - 1].compareTo(e) > 0; j--) {
//                a[j] = a[j - 1];
//            }
//            a[j] = e;
        }
        assert SortUtils.isSort(a, lo, hi);
    }

    public static void main(String[] args) {
        int N =50 ;
        Integer[] arr = SortUtils.generateOrderArray(N);
        SortUtils.shuffle(arr);
        Integer[] arr2=Arrays.copyOf(arr, arr.length);
        System.out.println("before: ");

        Arrays.stream(arr).forEach(x-> System.out.print(x+"\t"));

        Insertion<Integer> insertion = new Insertion<>();
        insertion.sort(arr);
        System.out.println("\nafter: ");
        Arrays.stream(arr).forEach(x-> System.out.print(x+"\t"));

        System.out.println("\n=============");
        insertion.sort(arr2,0,arr2.length-1);
        System.out.println("\nafter: ");
        Arrays.stream(arr).forEach(x-> System.out.print(x+"\t"));
    }


}
