package com.future;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;

import static java.util.stream.Collectors.toList;

/**
 * @author louis
 * <p>
 * Date: 2019/8/22
 * Description:
 */
public class App {

    public static void main(String[] args) {
//        run1();
//        for (int i = 0; i < 5; i++) {
////            run2();
//            run3();
//        }
        run3();

    }
    public static void run1() {
        Shop shop = new Shop("BestShop");
        long start = System.nanoTime();
        Future<Double> futurePrice = shop.getPriceAsync2("my favorite product");
        long invocationTime = ((System.nanoTime() - start) / 1_000_000);
        System.out.println("Invocation returned after " + invocationTime
                + " msecs");
        try {
            double price = futurePrice.get();
            System.out.printf("Price is %.2f%n", price);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        long retrievalTime = ((System.nanoTime() - start) / 1_000_000);
        System.out.println("Price returned after " + retrievalTime + " msecs");
    }

    /**
     *
     */
    public static void run2() {
        List<Shop> shops = Arrays.asList(new Shop("BestPrice"),
                new Shop("LetsSaveBig"),
                new Shop("MyFavoriteShop"),
                new Shop("LLEages"),
                new Shop("Myabatss"),
                new Shop("BuyItAlssa"),
                new Shop("BuyItDDal"),
                new Shop("BuyItDDal"),
                new Shop("LLEages"),
                new Shop("Myabatss"),
                new Shop("BuyItAlssa"),
                new Shop("BuyItDDal"),
                new Shop("BuyItDDal")

        );
        long start = System.nanoTime();
        System.out.println(findPrices3(shops,"myPhone27S"));
        long duration = (System.nanoTime() - start) / 1_000_000;
        System.out.println("Done in " + duration + " msecs");
    }


    public static void run3() {
        List<Shop> shops = new ArrayList<>(225);
        for (int i = 0; i < 1; i++) {
            shops.add(new Shop("BuyItDDal"));

        }
        long start = System.nanoTime();
        System.out.println(findPrices5(shops,"myPhone27S"));
        long duration = (System.nanoTime() - start) / 1_000_000;
        System.out.println("Done in " + duration + " msecs");
    }

    public static List<Integer> findPrices5(List<Shop> shops, String product) {
        ExecutorService executor = Executors.newFixedThreadPool(8);
        List<CompletableFuture<Integer>> priceFutures = shops.stream().map(shop -> CompletableFuture.supplyAsync(() ->
                String.format("%s price is %.2f",
                        shop.getShopName(), shop.getPrice(product)), executor)
        ).map(future -> future.thenApply(String::hashCode)).collect(toList());
        executor.shutdown();
        return priceFutures.stream().map(CompletableFuture::join).collect(toList());


    }


//    public static List<String> findPrice5(List<Shop> shops, String product) {
//        ExecutorService executorService = Executors.newCachedThreadPool();
//        Callable callable=new Callable() {
//            @Override
//            public Object call() throws Exception {
//
//            }
//        }
//        shops.stream().map(shop -> executorService.execute())
//    }

    /**
     *
     * @param shops
     * @param product
     * @return
     */
    public static List<String> findPrices4(List<Shop> shops,String product) {
        Executor executor = Executors.newFixedThreadPool(128);
        List<CompletableFuture<String>> priceFutures = shops
                .stream()
                .map(shop -> CompletableFuture.supplyAsync(() ->
                        String.format("%s price is %.2f",
                                shop.getShopName(), shop.getPrice(product)), executor)
        )
//                .map(future->future.thenApply())
//                .map(future->future.thenCompose())
                .collect(toList());
        return priceFutures.stream().map(CompletableFuture::join).collect(toList());


    }

    public static List<String> findPrices3(List<Shop> shops,String product) {
        List<CompletableFuture<String>> priceFutures =
                shops.stream()
                        .map(shop -> CompletableFuture.supplyAsync(
                                () ->
                                        String.format("%s price is %.2f",
                                                shop.getShopName(), shop.getPrice(product))

                        ))
                        .collect(toList());
        return priceFutures.stream()
                .map(CompletableFuture::join)
                .collect(toList());
    }







    public static List<String> findPrices2(List<Shop> shops,String product) {
        return shops.parallelStream()
                .map(shop -> String.format("%s price is %.2f",
                        shop.getShopName(), shop.getPrice(product)))
                .collect(toList());
    }



    public static List<String> findPrices1(List<Shop> shops,String product) {
        return shops.stream()
                .map(shop -> String.format("%s price is %.2f",
                        shop.getShopName(), shop.getPrice(product)))
                .collect(toList());

    }
}
