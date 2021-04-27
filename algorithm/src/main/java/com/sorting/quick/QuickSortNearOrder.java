package com.sorting.quick;

import com.sorting.common.SortUtils;
import com.sorting.insert.Insertion;


/**
 * @author louis
 * <p>
 * Date: 2019/9/17
 * Description:
 */
public class QuickSortNearOrder<T extends Comparable<T>> extends AbstractQuickSort<T>{


    /**
     *快排优化点，
     *  2020/1/6  有一个问题，在排序的最后两个元素不是有序的，需要优化
     * @param arr
     * @param lo
     * @param hi
     */
    public void quickSort(T[] arr, int lo, int hi) {
        if (hi - lo <= 15) {
            Insertion<T> insertSort = new Insertion<>();
            insertSort.sort(arr, lo, hi);
            return;
        }
        int p = partition(arr, lo, hi);
        quickSort(arr, lo,p-1);
        quickSort(arr, p + 1, hi);

    }

    /**
     * 基本的快排，如果当数组基本有序，
     * 那么这个时候快排的效率就很低效O(n^2)
     * 基本有序的时候，递归树就会退化成一个非平衡树，最差的情况递归树就会退化成一个
     * 链表
     *
     *    对arr[l...r]部分进行partition操作
     *    返回p, 使得arr[l...p-1] < arr[p] ; arr[p+1...r] > arr[p]
     * 解决方式就是并不是对数组第一个元素作为标定点，而是随机以为元素，虽然这个时候还是可能会
     * 退化为一个链表，但是概率极低
     * 虽然这样优化了，但是对于大部分数字都相等的数组，依然会退化成O(n^2)
     *
     * @param arr
     * @param lo
     * @param hi
     */
    public int partition(T[] arr, int lo, int hi) {
        SortUtils.exch(arr, lo, (int) (Math.random()*(hi-1+1))+1);
        T v = arr[lo];
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
