package com.louis.future;

import org.junit.Test;

import java.util.Random;
import java.util.concurrent.CompletableFuture;

/**
 * @author louis
 * <p>
 * Date: 2019/8/23
 * Description:
 */
public class Completable {

    static Random random = new Random();
    static long t = System.currentTimeMillis();
    static public int getMoreData() {
        System.out.println("begin to start compute");
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("end to start compute. passed " + (System.currentTimeMillis() - t)/1000 + " seconds");
        return random.nextInt(1000);
    }

    @Test
    public void test() {

        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(Completable::getMoreData);

        CompletableFuture<Integer> complete = future.whenComplete((x, v) -> {
            System.out.println(x);
            System.out.println(v);
        });
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(complete.join());
    }

    @Test
    public void test1() {

        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(Completable::getMoreData);

        CompletableFuture<Integer> complete = future.handle((x, v) -> {
            System.out.println("future .get"+x);
            int i = x + 10;
            return i;

        });
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(complete.join());
    }

    @Test
    public void test2() {

        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> 100)
                .thenApply(integer -> integer / 10)
                .thenApply(integer -> integer.toString() + "heihei");

        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(future.join());
    }
    @Test
    public void test3() {

        CompletableFuture<Void> future = CompletableFuture.supplyAsync(() -> 100)
                .thenAcceptBoth(CompletableFuture.completedFuture(10),
                        (x, y) -> System.out.println(x * y));

        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(future.join());
    }
}
