package com.sorting.quick;

import com.sorting.common.SortUtils;

import java.util.Arrays;

/**
 * @author louis
 * <p>
 * Date: 2020/1/6
 * Description:
 */
public class QuickSortApp {

    public static void main(String[] args) {

        AbstractQuickSort<Integer> merge = new QuickSortThreadWay<>();
        int N =16 ;
        Integer[] arr = SortUtils.generateOrderArray(N);
        SortUtils.shuffle(arr);
        System.out.println("before: ");

        Arrays.stream(arr).forEach(x-> System.out.print(x+"\t"));

        long timeMillis = System.currentTimeMillis();
        merge.sort(arr);
        System.out.println(" ");
        System.out.println("after: ");
        long endTime = System.currentTimeMillis();
        System.out.println("快排1： 消耗时间" + (endTime - timeMillis));
        Arrays.stream(arr).forEach(x-> System.out.print(x+"\t"));

    }
}
