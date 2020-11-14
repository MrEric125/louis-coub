package com.sorting.heap;

/**
 * @author John·Louis
 * @date create in 2019/9/1
 * description:
 */
public class HeapSort<T extends Comparable<T>> {

    /**
     * 时间复杂度为O(n*logn)
     * @param arr
     * @param n
     */
    void heapSort1(T[] arr, int n) {

        //入堆
        MaxHeap<T> maxHeap = new MaxHeap<>(n);
        for (int i = 0; i < n; i++) {
            maxHeap.insert(arr[i]);
        }
//        出堆
        for (int i =n-1; i >=0; i--) {
            arr[i] = maxHeap.extractMax();
        }
    }
    void heapsort2(T[] arr) {
        MaxHeap<T> maxHeap = new MaxHeap<>(arr, arr.length);
    }
    private void swap(T[] arr, int i, int j) {
        T item = arr[i];
        arr[i] = arr[j];
        arr[j] = item;
    }

    /**
     * 原地堆排序
     * @param arr
     * @param n
     * @param k
     */
    private void shiftDown(T[] arr, int n, int k) {
        while (2 * k+1 < n) {
//            items[k] 和items[j] 交换位置
            int j = 2 * k+1;
            if (j + 1 <= n && arr[j + 1].compareTo(arr[j]) > 0) {
                j += 1;
            }
            if (arr[k].compareTo(arr[j]) >= 0) {
                break;
            }
            swap(arr,k, j);
            k = j;
        }

    }

    /**
     * 原地堆排序
     */
    void heapsort(T[] arr, int n) {
        for (int i = (n-1); i >=0; i--) {
            shiftDown(arr, n, i);
        }
        for (int i = n-1 ; i >0; i--) {
            shiftDown(arr, i, 0);
        }
    }

}
