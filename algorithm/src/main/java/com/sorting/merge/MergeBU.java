package com.sorting.merge;


import com.sorting.common.ISorting;
import com.sorting.common.SortUtils;

import java.util.Arrays;

/**
 * create by louis
 * <p> Date: 2018-12-06
 * <p> Version:1.0 自底向上的归并排序
 */
public class MergeBU<T extends Comparable<T>> implements ISorting<T> {




    public static void main(String[] args) {

        MergeBU merge = new MergeBU();
        int N = 10;
        Comparable[] arr = SortUtils.generateOrderArray(N);
        SortUtils.shuffle(arr);
        System.out.println("before: ");

        Arrays.stream(arr).forEach(x-> System.out.print(x+"\t"));

        long timeMillis = System.currentTimeMillis();
        merge.sort(arr);
        System.out.println(" ");
        System.out.println("after: ");
        long endTime = System.currentTimeMillis();
        System.out.println("消耗时间" + (endTime - timeMillis));
        Arrays.stream(arr).forEach(x-> System.out.print(x+"\t"));

    }

    /**
     * 自底向上的归并排序
     * @param arr
     */
    @Override
    public void sort(Comparable[] arr) {

        int n = arr.length;
//        Comparable[] aux = new Comparable[n];
//        todo 小数组的时候可以使用插入排序，
//        虽然这个地方时双重循环，但是这个地方的时间复杂度依然是nlogn
        for (int size = 1; size < n; size *= 2) {
            for (int j = 0; j < n-size ; j += size + size) {
                int mid = j + size - 1;
////              size+2j-1可能会越界，如果越界那么就取n-1

//
                int hi = Math.min(size + size+j- 1, n - 1);
////               对arr[j...j+size-1]和arr[j+size...j+2*size-1]的数组进行归并
                merge(arr, j, mid, hi);
            }
        }
        assert SortUtils.isSort(arr);
    }

    // 将arr[l...mid]和arr[mid+1...r]两部分进行归并
    private static void merge(Comparable[] arr, int l, int mid, int r) {

        Merge merge = new Merge();
        merge.merge(arr, l, mid, r);

    }
}
