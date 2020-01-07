package com.sorting.quick;

import com.sorting.common.SortUtils;
import com.sorting.insert.Insertion;

/**
 * 三路快排算法也是一个时间复杂度为O(nlogn)
 * 可在一秒的时间内处理百万数量数据
 * @param <T>
 */
public class QuickSortThreadWay <T extends Comparable<T>> extends AbstractQuickSort<T>{
    // 递归使用快速排序,对arr[l...r]的范围进行排序
    public   void quickSort(T[] arr, int l, int r){

        // 对于小规模数组, 使用插入排序
        if( r - l <= 15 ){
            Insertion<T> insertion = new Insertion<>();
            insertion.sort(arr, l, r);
            return;
        }

        // 随机在arr[l...r]的范围中, 选择一个数值作为标定点pivot
        swap( arr, l, (int)(Math.random()*(r-l+1)) + l );

        T v = arr[l];

        int lt = l;     // arr[l+1...lt] < v
        int gt = r + 1; // arr[gt...r] > v
        int i = l+1;    // arr[lt+1...i) == v
        while( i < gt ){
            if( arr[i].compareTo(v) < 0 ){
                swap( arr, i, lt+1);
                i ++;
                lt ++;
            }
            else if( arr[i].compareTo(v) > 0 ){
                swap( arr, i, gt-1);
                gt --;
            }
            else{ // arr[i] == v
                i ++;
            }
        }

        swap( arr, l, lt );

        quickSort(arr, l, lt-1);
        quickSort(arr, gt, r);
    }

    @Override
    public int partition(T[] arr, int lo, int hi) {
        return 0;
    }


    private static void swap(Object[] arr, int i, int j) {
        Object t = arr[i];
        arr[i] = arr[j];
        arr[j] = t;
    }

//
}
