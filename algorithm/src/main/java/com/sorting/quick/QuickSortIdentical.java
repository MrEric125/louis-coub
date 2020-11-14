package com.sorting.quick;

import com.sorting.common.SortUtils;
import com.sorting.insert.Insertion;


/**
 * @author louis
 * <p>
 * Date: 2020/1/6
 * Description:
 * Quick Sort也是一个O(nlogn)复杂度的算法
 */
public class QuickSortIdentical <T extends Comparable<T>> extends AbstractQuickSort<T> {


    // 对arr[l...r]部分进行partition操作
    // 返回p, 使得arr[l...p-1] < arr[p] ; arr[p+1...r] > arr[p]
    public   int partition(Comparable[] arr, int l, int r){

        // 随机在arr[l...r]的范围中, 选择一个数值作为标定点pivot
        swap( arr, l , (int)(Math.random()*(r-l+1))+l );

        Comparable v = arr[l];

        int j = l; // arr[l+1...j] < v ; arr[j+1...i) > v
        for( int i = l + 1 ; i <= r ; i ++ )
            if( arr[i].compareTo(v) < 0 ){
                j ++;
                swap(arr, j, i);
            }

        swap(arr, l, j);

        return j;
    }

    // 递归使用快速排序,对arr[l...r]的范围进行排序
    public  void quickSort(T[] arr, int l, int r){

        // 对于小规模数组, 使用插入排序
        if( r - l <= 15 ){
            Insertion<T> insertion = new Insertion<>();
            insertion.sort(arr, l, r);
            return;
        }

        int p = partition(arr, l, r);
        quickSort(arr, l, p-1 );
        quickSort(arr, p+1, r);
    }



    private static void swap(Object[] arr, int i, int j) {
        Object t = arr[i];
        arr[i] = arr[j];
        arr[j] = t;
    }

//    // 测试 QuickSort
//    public static void main(String[] args) {
//
//        // Quick Sort也是一个O(nlogn)复杂度的算法
//        // 可以在1秒之内轻松处理100万数量级的数据
//        int N = 1000000;
//        Integer[] arr = SortTestHelper.generateRandomArray(N, 0, 100000);
//        SortTestHelper.testSort("bobo.algo.QuickSort", arr);
//
//        return;
//    }
}
