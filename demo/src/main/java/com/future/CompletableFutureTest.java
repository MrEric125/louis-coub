package com.future;

import org.assertj.core.util.Lists;
import org.junit.Test;

import java.util.List;
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Supplier;
import java.util.stream.Collectors;

/**
 * @author louis
 * <p>
 * Date: 2019/8/27
 * Description:
 */
public class CompletableFutureTest {


    @Test
    public void thenApplyExample() {
        CompletableFuture<String> message = CompletableFuture.completedFuture(message(()->"zz"))
                .thenApply(String::toUpperCase);
        System.out.println(message.join());
        CompletableFuture completableFuture = new CompletableFuture();

        String join = CompletableFuture
                .supplyAsync(()->"method")
                .whenComplete((x, y) -> System.out.println(x.substring(1)))
                .join();
        System.out.println(join);
    }

    @Test
    public void thenCompose() {

        List<CompletableFuture> list = Lists.newArrayList();
        ExecutorService executor = Executors.newFixedThreadPool(8);
        for (int i = 0; i < 8; i++) {
            list.add(CompletableFuture.supplyAsync(() -> calculate(8)));
        }
        long start = System.currentTimeMillis();
        List<Integer> collect = list.stream().map(CompletableFuture<Integer>::join).collect(Collectors.toList());
        long end = System.currentTimeMillis();
        System.out.println("total cost: " + (end - start));
        executor.shutdown();
        collect.forEach(System.out::println);
        int max = collect.stream().mapToInt(Integer::valueOf).max().orElse(0);
        System.out.println("max num  " + max);

        long start1= System.currentTimeMillis();
        for (int i = 0; i <8 ; i++) {
            calculate(8);
        }
        long end1 = System.currentTimeMillis();
        System.out.println("total cost: " + (end1 - start1));

    }

    public int calculate(int num) {
        Random random = new Random();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return random.nextInt(num);
    }

    public String message(Supplier<String> a) {
        return "message";
    }

}
