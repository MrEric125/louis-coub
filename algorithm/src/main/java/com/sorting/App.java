package com.sorting;

import com.sorting.common.ISorting;
import com.sorting.common.SortUtils;
import com.sorting.merge.MergeBU;
import com.web.AlgorithmService;
import org.assertj.core.util.Lists;

import java.util.List;



/**
 * create by louis
 * <p> Date: 2018-12-04
 * <p> Version:1.0
 */
public class App {
    public static void main(String[] args) throws Exception{

        AlgorithmService service = new AlgorithmService();

        ISorting<Integer> shell = new MergeBU<>();
        int N = 100000;
        Integer[] arr = SortUtils.generateOrderArray(N);

        List<Long> costList =Lists.newArrayList();


        long total = 0;
        for (int i = 0; i < 8; i++) {
            SortUtils.shuffle(arr);
            long cost =service.calculateCost(shell, arr);
            total = total + cost;
            System.out.println(cost);
            costList.add(cost);
        }
        System.out.println("最小值" +service.minCost(costList));
        System.out.println("最大值" + service.maxCost(costList));
        System.out.println("avg cost" + service.avgCost(costList));
    }


}
