package com.louis;

import org.junit.Test;

import java.util.concurrent.*;

/**
 * @author louis
 * <p>
 * Date: 2019/8/21
 * Description:
 */
public class FutureTest {

    @Test
    public void test() {
        Runnable runnable = () -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }


        };
        Thread thread = new Thread( runnable,"a");

        FutureTask<String > futureTask = new FutureTask<>(thread,null);
        try {
            long currentTime = System.currentTimeMillis();

            String o = futureTask.get();
            long endTime = System.currentTimeMillis();
            System.out.println(o);
            System.out.println(endTime - currentTime);

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

    }
    @Test
    public void test2() throws ExecutionException, InterruptedException {
        Callable<String > runnable = () -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "FUTURETASK";


        };
        FutureTask<String > future = new FutureTask<>(runnable);
        Thread thread = new Thread(future);
        thread.start();
        long startTime = System.currentTimeMillis();
        System.out.println("11");
        System.out.println(future.get());
        long endTime = System.currentTimeMillis();
        System.out.println(endTime-startTime);


    }
    @Test
    public void test3() throws ExecutionException, InterruptedException {
        Future<Double> priceAsync = getPriceAsync("LOL");
        long startTime = System.currentTimeMillis();
        System.out.println("11");
        System.out.println(priceAsync.get());
        long endTime = System.currentTimeMillis();
        System.out.println(endTime-startTime);

    }
    @Test
    public void test4() throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool();
        Future<Double> priceAsync = getPriceAsync(executorService,"LOL");
        long startTime = System.currentTimeMillis();
        System.out.println("11");
        System.out.println(priceAsync.get());
        long endTime = System.currentTimeMillis();
        System.out.println(endTime-startTime);


    }

    private Future<Double> getPriceAsync(ExecutorService executorService, String product) {
//        CompletableFuture<Double> future = new CompletableFuture<>();
        Callable<Double> runnable=() -> {
           return doSomeThing(product);
//            future.complete(price);
        };
        return executorService.submit(runnable);
//        return future;
    }

    private Future<Double> getPriceAsync(String product) {
        CompletableFuture<Double> future = new CompletableFuture<>();
        Runnable runnable=() -> {
            double price = doSomeThing(product);
            future.complete(price);
        };
        new Thread(runnable).start();
        return future;
    }
    private double doSomeThing(String product) {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return product.charAt(0) * 12.36;
    }
}
