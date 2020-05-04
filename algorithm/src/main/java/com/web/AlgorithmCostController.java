package com.web;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sorting.common.ISorting;
import com.sorting.common.SortUtils;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.util.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.TaskExecutor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("/algo")
public class AlgorithmCostController {

    @Autowired
    private AlgorithmService algorithmService;

    @Autowired
    private TaskExecutor taskExecutor;




    /**
     * 计算某个算法所花费的平均时间
     * @param algoName 算法名字
     * @param runTime 运行次数
     * @return 算法计算最后花费的平均时间
     */
    @RequestMapping("/costAsync/{algoName}/{runTime}")
    public String costAsync(@PathVariable String algoName, @PathVariable long runTime) {
        ISorting<Integer> sortService = algorithmService.algoName(algoName);
        int N = 100000;
        Integer[] arr = SortUtils.generateOrderArray(N);

        List<CompletableFuture> completableFutureList = Lists.newArrayList();
        for (int i = 0; i < runTime; i++) {
            SortUtils.shuffle(arr);
            completableFutureList.add(CompletableFuture.supplyAsync(() -> algorithmService.calculateCost(sortService, arr), taskExecutor));
        }



        long start = System.currentTimeMillis();
        List<Long> costList = completableFutureList.stream().map(CompletableFuture<Long>::join).collect(Collectors.toList());
        long end = System.currentTimeMillis();
        log.info("异步执行总共花费的时间:{} ", (end - start));
        log.info("执行花费时间详细情况:{}", JSON.toJSONString(costList));

        double avgCost= algorithmService.avgCost(costList);
        return avgCost + "";
    }

    @RequestMapping("/cost/{algoName}/{runTime}")
    public String cost(@PathVariable String algoName, @PathVariable long runTime) {
        ISorting<Integer> sortService = algorithmService.algoName(algoName);
        int N = 100000;
        Integer[] arr = SortUtils.generateOrderArray(N);

        List<Long> costList = Lists.newArrayList();
        for (int i = 0; i < runTime; i++) {
            SortUtils.shuffle(arr);
            costList.add(algorithmService.calculateCost(sortService, arr));
        }
        log.info("执行花费时间详细情况:{}", JSON.toJSONString(costList));
        double avgCost= algorithmService.avgCost(costList);
        return avgCost + "";
    }









}
