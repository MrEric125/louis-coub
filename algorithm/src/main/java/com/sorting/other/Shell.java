package com.sorting.other;

import com.sorting.common.ISorting;
import com.sorting.common.SortUtils;

/**
 * create by louis
 * <p> Date: 2018-12-05
 * <p> Version:1.0
 *
 * 希尔排序:
 * 基本思路：先将整个待排序的记录序列分割成为若干子序列分别进行直接插入排序，
 *       ：待整个序列中的记录'基本有序'时候，再对全部记录进行一次直接插入排序
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
                for (int j = i; j >= h && SortUtils.less(a[j], a[j - h]); j -= h) {
                    SortUtils.exch(a, j, j - h);
                }
            }
            assert SortUtils.isHsort(a, h);
            h /= 3;
        }
    }
}
