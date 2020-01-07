package com.sorting.other;

import com.sorting.common.ISorting;
import com.sorting.common.SortUtils;

import java.util.Comparator;

/**
 * create by louis
 * <p> Date: 2018-12-04
 * <p> Version:1.0
 *
 * 选择排序，选择要求的值放在指定的位置上
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

    /**
     * todo
     * @param a
     * @param comparator
     */
    public void sort(Object[] a, Comparator comparator) {
        int n = a.length;
        for (int i = 0; i < n; i++) {
            int min = i;
        }
    }
}
