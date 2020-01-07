package com.sorting.common;

import java.util.Arrays;

/**
 * @author John·Louis
 * @date create in 2019/9/11
 * description:
 */
public class BaseMergeSort<T extends Comparable<T>> {


    /**
     * 将arr[l...mid]和arr[mid+1...r]两部分进行归并
     * @param arr 需要排序的数组
     * @param lo  排序的最低位
     * @param mid 数组的中位
     * @param hi 数组的高位
     */
    public void baseMerge(T[] arr ,int lo, int mid, int hi) {
//
        T[] aux = Arrays.copyOfRange(arr, lo, hi + 1);
        // 初始化，i指向左半部分的起始索引位置l；j指向右半部分起始索引位置mid+1
        int i = lo, j = mid + 1;
        for (int k = lo ; k <=hi ; k++) {
//            前面两个if 相当于判断索引是否合法
            // 如果左半部分元素已经全部处理完毕
            if (i>mid) {
//                这个地方的aux[j-lo] 是因为aux 每次都是从索引0开始的，如果aux是传入进来的话，
//                那么这个地方就不用aux[j-lo]
                arr[k] = aux[j - lo];
                j++;
                // 如果右半部分元素已经全部处理完毕
            } else if (j > hi) {
                arr[k] = aux[i - lo];
                i++;
                // 左半部分所指元素 < 右半部分所指元素
            } else if (SortUtils.less(aux[i - lo], aux[j - lo])) {
                arr[k] = aux[i - lo];
                i++;
                // 左半部分所指元素 >= 右半部分所指元素
            } else {
                arr[k] = aux[j - lo];
                j++;
            }
        }
    }

    /**
     * 归并排序用接口将a[lo....]和a[mid+1...hi]两部分数据进行归并
     * @param arr 需要排序的数组
     * @param aux 复制的aux
     * @param lo  排序的最低位
     * @param mid 数组的中位
     * @param hi 数组的高位
     */
    public void baseMerge(T[] arr, T[] aux, int lo, int mid, int hi) {
        //复制数组a
//        for (int i = lo; i <=hi ; i++) {
//            aux[i] = arr[i];
//        }
//        aux = Arrays.copyOfRange(a, lo, hi);
//        指向两个要合并数组开头的位置
        int i = lo, j = mid + 1;

        for (int k = lo; k <=hi; k++) {
            // 如果左半部分元素已经全部处理完毕
            if (i>mid)                      arr[k] = aux[j++];
                // 如果右半部分元素已经全部处理完毕
            else if(j>hi)                   arr[k] = aux[i++];
                // 左半部分所指元素 < 右半部分所指元素
            else if (SortUtils.less(aux[i],aux[j]))   arr[k] = aux[i++];
                // 左半部分所指元素 >= 右半部分所指元素
            else                            arr[k] = aux[j++];
        }

//
    }
}
