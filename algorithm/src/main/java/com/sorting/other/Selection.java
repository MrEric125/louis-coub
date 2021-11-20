package com.sorting.other;

import com.sorting.common.ISorting;
import com.sorting.common.SortUtils;
import com.sorting.insert.Insertion;

import java.util.Arrays;

/**
 * create by louis
 * <p> Date: 2018-12-04
 * <p> Version:1.0
 *
 * 选择排序，选择要求的值放在指定的位置上
 * 和插入排序的思想很像，都是分为已排序区间和未排序区间
 *
 * 插入排序是拿无需区间(初始大小为1)最前面一个做插入操作
 * 选择排序是选择无序区间(初始大小为0)最小值放在有序区间最后面
 */
public class Selection<T extends Comparable<T>> implements ISorting<T> {

    @Override
    public void sort(Comparable[] a) {
        int n = a.length;
        for (int i = 0; i < n; i++) {
            //将最小的拿到最前面
            int min = i;
            for (int j = i + 1; j < n; j++) {
                if (SortUtils.less(a[j],a[min])) {
                    min = j;
                }
            }
            SortUtils.exch(a, i, min);
            assert SortUtils.isSort(a, 0, i);
        }
        assert SortUtils.isSort(a);
    }


    public static void main(String[] args) {
        int N =50 ;
        Integer[] arr = SortUtils.generateOrderArray(N);
        SortUtils.shuffle(arr);
        System.out.println("before: ");

        Arrays.stream(arr).forEach(x-> System.out.print(x+"\t"));
        Selection<Integer> insertion = new Selection<>();
        insertion.sort(arr);
        System.out.println("\nafter: ");
        Arrays.stream(arr).forEach(x-> System.out.print(x+"\t"));

    }
}
