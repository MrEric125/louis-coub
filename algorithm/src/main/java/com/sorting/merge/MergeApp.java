package com.sorting.merge;

import com.sorting.common.SortUtils;

import java.util.Arrays;

/**
 * @author John·Louis
 * @date create in 2019/9/11
 * description:
 */
public class MergeApp {

    public static void main(String[] args) {
        Merge<Integer> merge = new Merge<>();


        int N = 10;
        Integer[] arr = SortUtils.generateOrderArray(N);
        SortUtils.shuffle(arr);
        System.out.println("before: ");

        Arrays.stream(arr).forEach(x-> System.out.print(x+"\t"));

        long timeMillis = System.currentTimeMillis();
        merge.sort(arr);
        System.out.println("\nafter: ");
        long endTime = System.currentTimeMillis();
        System.out.println("消耗时间" + (endTime - timeMillis));
        Arrays.stream(arr).forEach(x-> System.out.print(x+"\t"));
    }
}
