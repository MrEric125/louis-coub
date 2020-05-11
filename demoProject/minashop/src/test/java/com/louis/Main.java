package com.louis;

import com.google.common.collect.Maps;

import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * @author John·Louis
 * @date created on 2020/2/26
 * description:
 */
public class Main {

    public static void main(String[] args) throws Exception {
        thenApply();

    }

    private static void thenApply() throws Exception {
        CompletableFuture<Long> future = CompletableFuture.supplyAsync(() -> {
            long result = new Random().nextInt(100);
            System.out.println("result1="+result);
            return result;
        }).thenApply(t -> {
            long result = t*5;
            System.out.println("result2="+result);
            return result;
        });

        long result = future.get();
        System.out.println(result);


        Executor executor = Executors.newSingleThreadExecutor();
        CompletableFuture.supplyAsync(() -> {
            long result2 = new Random().nextInt(100);
            System.out.println("result1=" + result);
            return result;
        }, executor);
    }
}
