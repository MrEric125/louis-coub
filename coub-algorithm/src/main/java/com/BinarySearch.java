package com;

public class BinarySearch<T> {
    /**
     * 二分查找 的变种 floor ceil
     *
     * @param arr    有序数组
     * @param n      数组长度
     * @param target 查找目标
     * @return
     */
    public int find(T[] arr, int n, T target) {

        int l = 0, r = n - 1;

        while (l <= r) {
            int mid = (l + r) / 2;

            mid = l + (r - l) / 2;

            if (arr[mid] == target) {
                return mid;
            }


        }
        return 0;


    }
}
