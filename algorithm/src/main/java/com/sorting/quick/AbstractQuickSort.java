package com.sorting.quick;

import com.sorting.common.ISorting;

import java.util.Arrays;
import java.util.stream.Collectors;

public abstract class AbstractQuickSort<T extends Comparable<T>>  implements ISorting<T> {
    @Override
    public void sort(T[] a) {
        quickSort(a,0,a.length-1);
    }

    public abstract void quickSort(T arr[], int lo, int hi);

    public abstract int partition(T[] arr, int lo, int hi);

}
