package com.sorting.insert;

import com.sorting.common.ISorting;
import com.sorting.common.SortUtils;


/**
 * create by louis
 * <p> Date: 2018-12-04
 * <p> Version:1.0
 */
public class InsertionX<T extends Comparable<T>> implements ISorting<T> {


    @Override
    public void sort(T[] a) {
        int n = a.length;
        int exchanges = 0;
        for (int i = n - 1; i > 0; i--) {
            if (SortUtils.less(a[i], a[i - 1])) {
                SortUtils.exch(a, i, i - 1);
                exchanges++;
            }
        }
        if (exchanges == 0) {
            return;
        }
        for (int i = 2; i < n; i++) {
            T v = a[i];
            int j = i;
            while (SortUtils.less(v, a[j - 1])) {
                a[j] = a[j - 1];
                j--;
            }
            a[j] = v;
        }
        assert SortUtils.isSort(a);
    }

}
