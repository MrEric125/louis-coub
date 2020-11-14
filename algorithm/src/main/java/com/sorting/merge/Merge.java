package com.sorting.merge;

import com.sorting.common.BaseMergeSort;
import com.sorting.common.ISorting;
import com.sorting.common.SortUtils;

import java.util.Arrays;


/**
 * create by louis
 * <p> Date: 2018-12-05
 * <p> Version:1.0
 * 归并排序：自顶向下的归并排序
 */
public class Merge<T extends Comparable<T>> extends BaseMergeSort<T> implements ISorting<T> {

    /**
     *
     * @param a
     * @param aux
     * @param lo
     * @param mid
     * @param hi
     */
    private void merge(T[] a, T[] aux, int lo, int mid, int hi) {

        baseMerge(a, aux, lo, mid, hi);
    }

    public void merge(T[] a, int lo, int mid, int hi) {
        baseMerge(a, lo, mid, hi);
    }

    @Override
    public void sort(T[] a) {
        T[] aux = Arrays.copyOfRange(a, 0, a.length);

        sort(a,0,a.length-1);
//        sort(a, aux);
        assert SortUtils.isSort(a);
    }

    /**
     * todo 这种方式的排序还有问题，后期优化
     * @param a
     * @param aux
     */
    public void sort(T[] a,T[] aux) {
        sort(a,aux,0,a.length-1);
        assert SortUtils.isSort(a);
    }

    /**
     * 递归方式实现
     * @param a
     * @param aux
     * @param lo
     * @param hi
     */
    public void sort(T[] a, T[] aux, int lo, int hi) {
        if (hi<=lo) return;
        //取到中位数
        int mid = lo + (hi - lo) / 2;
        sort(a, aux, lo, mid);
        sort(a, aux, mid + 1, hi);
        if (a[mid].compareTo(a[mid+1])>0) {
            merge(a, aux,lo, mid, hi);
        }
    }

    public void sort(T[] arr, int lo, int hi) {
        if (hi<=lo) return;
//        if (hi-lo<=15) {
//        小数组的是时候优化点
//            可以使用插入排序的方式让归并排序优化
//        }
        //取到中位数
        int mid = lo + (hi - lo) / 2;
        sort(arr,  lo, mid);
        sort(arr,  mid + 1, hi);
//        对于近乎有序的数组的优化
        if (arr[mid].compareTo(arr[mid+1])>0) {
            merge(arr, lo, mid, hi);
        }
    }



}
