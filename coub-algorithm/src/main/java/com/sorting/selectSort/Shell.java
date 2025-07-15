package com.sorting.selectSort;

import com.sorting.common.ISorting;
import com.sorting.common.SortUtils;

import java.util.Arrays;

/**
 * create by louis
 * <p> Date: 2018-12-05
 * <p> Version:1.0
 * <p>
 * 希尔排序:
 * 基本思路：先将整个待排序的记录序列分割成为若干子序列分别进行直接插入排序，
 * ：待整个序列中的记录'基本有序'时候，再对全部记录进行一次直接插入排序
 */
public class Shell<T extends Comparable<T>> implements ISorting<T> {
    @Override
    public void sort(Comparable[] a) {
        int n = a.length;
        int h = 1;
        //n/3表示等分成多少份，在while中要同样设置
        while (h < n / 3) {
            h = 3 * h + 1;
        }
        while (h >= 1) {
            for (int i = h; i < n; i++) {
                //j-=h   ====>: j=j-h
                for (int j = i; j >= h; j -= h) {
                    if (SortUtils.less(a[j], a[j - h])) {
                        SortUtils.exch(a, j, j - h);
                    }
                }
            }
            assert SortUtils.isHsort(a, h);
            h /= 3;
        }
    }

    public static void main(String[] args) {
        int N = 50;
        Integer[] arr = SortUtils.generateOrderArray(N);
        SortUtils.shuffle(arr);
        System.out.println("before: ");

        Arrays.stream(arr).forEach(x -> System.out.print(x + "\t"));
        Shell<Integer> shell = new Shell<>();
        shell.sort(arr);
        System.out.println("\nafter: ");
        Arrays.stream(arr).forEach(x -> System.out.print(x + "\t"));

    }
}
