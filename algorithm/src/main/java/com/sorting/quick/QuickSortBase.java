package com.sorting.quick;

import com.sorting.common.ISorting;
import com.sorting.common.SortUtils;
import com.sorting.insert.Insertion;

import java.util.Arrays;

/**
 * @author John·Louis
 * @date create in 2019/8/18
 * description: 快排的一般的速度还是要比归并排序要快一些的
 *
 *<p>归并排序和快速排序的思路都是使用了分治算法</p>
 *
 */
public class QuickSortBase<T extends Comparable<T>> extends AbstractQuickSort<T>{


    public void quickSort(T arr[],int lo,int hi) {
        if (lo >= hi)
            return;
        int p = partition(arr, lo, hi);
        quickSort(arr, lo,p-1);
        quickSort(arr, p + 1, hi);
    }

    /**
     * 返回
     * @param arr
     * @param lo
     * @param hi
     * @return p位置，使得arr[lo..p-1]< arr[p],arr[p+1]> arr[p]
     */
    public int partition(T[] arr, int lo, int hi) {
        Comparable v = arr[lo];
//        arr[lo+1..j]<v;  arr[j+1...i]>v
        int j = lo;
        for (int i = lo+1; i <=hi ; i++) {
            if (SortUtils.less(arr[i],v)) {
                SortUtils.exch(arr, j+1, i);
                j++;
            }
        }
        SortUtils.exch(arr, lo, j);
        return j;
    }

}
