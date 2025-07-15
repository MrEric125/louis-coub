package com.web;

import com.sorting.common.ISorting;
import com.sorting.insert.BinaryInsertion;
import com.sorting.insert.Insertion;
import com.sorting.insert.InsertionX;
import com.sorting.merge.Merge;
import com.sorting.merge.MergeBU;
import com.sorting.quick.QuickSortBase;
import com.sorting.quick.QuickSortIdentical;
import com.sorting.quick.QuickSortNearOrder;
import com.sorting.quick.QuickSortThreadWay;
import com.sorting.selectSort.Selection;
import com.sorting.selectSort.Shell;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author louis
 * @Date 2020/1/6
 * description:
 */
@Service
public class AlgorithmService {

    private Map<String, ISorting<Integer>> sortingMap = new ConcurrentHashMap<>();

    @PostConstruct()
    public void init() {
        sortingMap.put("BinaryInsertion", new BinaryInsertion<>());
        sortingMap.put("Insertion", new Insertion<>());
        sortingMap.put("InsertionX", new InsertionX<>());
        sortingMap.put("Merge", new Merge<>());
        sortingMap.put("MergeBU", new MergeBU<>());
        sortingMap.put("Selection", new Selection<>());
        sortingMap.put("Shell", new Shell<>());
        sortingMap.put("QuickSortBase", new QuickSortBase<>());
        sortingMap.put("QuickSortIdentical", new QuickSortIdentical<>());
        sortingMap.put("QuickSortNearOrder", new QuickSortNearOrder<>());
        sortingMap.put("QuickSortThreadWay", new QuickSortThreadWay<>());
    }

    public long minCost(List<Long> costList) {
        return costList.stream().mapToLong(x -> x).min().orElse(0);
    }


    public ISorting<Integer> algoName(String algoName) {
        return sortingMap.get(algoName);
    }

    /**
     * 去掉一个最低的，去掉一个最高的，然后求平均值
     *
     * @param costList
     * @return
     */
    public double avgCost(List<Long> costList) {
        return costList.stream()
                .mapToLong(x -> x)
                .filter(x -> x != maxCost(costList))
                .filter(x -> x != minCost(costList))
                .average().orElse(0);

    }

    public long maxCost(List<Long> costList) {
        return costList.stream().mapToLong(x -> x).max().orElse(0);
    }

    public long calculateCost(ISorting<Integer> sorting, Integer[] arr) {
        long startTime = System.currentTimeMillis();
        sorting.sort(arr);
        long endTime = System.currentTimeMillis();
        return endTime - startTime;
    }
}
