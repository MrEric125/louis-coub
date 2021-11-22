package com.sorting.insert;

import com.sorting.common.ISorting;
import com.sorting.common.SortUtils;

import java.util.Arrays;


/**
 * create by louis
 * <p> Date: 2018-12-04
 * <p> Version:1.0
 * 二分插入排序
 * 优化为之后的时间复杂度为 O(n*logn)
 */
public class BinaryInsertion<T extends Comparable<T>> implements ISorting<T> {

    @Override
    public void sort(T[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }

        for(int i = 1; i < arr.length; i++) {
            T temp = arr[i];
            // 通过二分查找找到插入的位置
            int insertIndex = findInsertIndex(arr, 0, i - 1, arr[i]);
            // 插入位置之后的元素依次向后移动
            if (i - insertIndex >= 0) System.arraycopy(arr, insertIndex, arr, insertIndex + 1, i - insertIndex);
            // 更新插入位置的值
            arr[insertIndex] = temp;
        }
        assert SortUtils.isSort(arr);
    }

    /**
     * 在有序数组 nums 的[L, R]部分上，找到 value 的插入位置
     */
    private  int findInsertIndex(T[] arr, int lo, int hi, T value) {

        while(lo <=hi ) {
            int mid = lo + ((hi - lo) / 2);
            if (SortUtils.less(arr[mid], value)) {
                lo = mid + 1;
            } else {
                hi = mid - 1;
            }
        }

        return lo;

    }

    public static void main(String[] args) {
        int N =50 ;
        Integer[] arr = SortUtils.generateOrderArray(N);
        SortUtils.shuffle(arr);
        System.out.println("before: ");

        Arrays.stream(arr).forEach(x-> System.out.print(x+"\t"));
        BinaryInsertion<Integer> shell = new BinaryInsertion<>();
        shell.sort(arr);
        System.out.println("\nafter: ");
        Arrays.stream(arr).forEach(x-> System.out.print(x+"\t"));

    }
}
