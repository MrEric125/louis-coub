package com.sorting.slidingwindow;

public class SlidingWindow {

    public static void main(String[] args) {



    }

    /**
     * 使用滑动床都的方式计算
     * 给定一个整数数组，计算长度为 'k' 的连续子数组的最大总和。
     * 输入： arr [] ={100,200,300,400}
     *          k=2
     * 输出：700
     *
     * @param arr
     * @param k
     * @return
     */
    public static Integer slidingWindows(Integer[] arr, int k) {
        int length = arr.length;
        int maxSum = 0;
        if (k>length){
            // 所有数据相加
            for (int i = 0; i < k; i++) {
                maxSum += arr[i];
            }
            return maxSum;
        }

        for (int i = 0; i < k; i++) {
            maxSum += arr[i];
        }
        int sum = maxSum;
        // 当前窗口和=前一个窗口和-前一个窗口第一个值+当前窗口最后一个值
        for (int i = k; i < length; i++) {
            sum += arr[i] - arr[i - k];
            maxSum = Math.max(maxSum, sum);

        }
        return maxSum;
    }



}
