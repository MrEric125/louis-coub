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
        List<Shop> shops = Shop.creatShop(null);
        long start = System.nanoTime();
        System.out.println(runStratge(shops, "myPhone27S", 3));
        long duration = (System.nanoTime() - start) / 1_000_000;
        System.out.println("Done in " + duration + " msecs");
    }


    public static void run3() {
        List<Shop> shops = Shop.creatShop(null);
        long start = System.nanoTime();
        System.out.println(runStratge(shops, "myPhone27S", 5));
        long duration = (System.nanoTime() - start) / 1_000_000;
        System.out.println("Done in " + duration + " msecs");
    }

    /**
     * 运行策略，具体使用哪种计算方式来计算
     */
    private static Object runStratge(List<Shop> shops, String product, int stratageCode) {
        Object object;
        switch (stratageCode) {
            case 1:
                object= findPrices1(shops, product);
                break;
            case 2:
                object= findPrices2(shops, product);
                break;
            case 3:
                object= findPrices3(shops, product);
                break;
            case 4:
                object= findPrices4(shops, product);
                break;
            case 5:
                object= findPrices5(shops, product);
                break;
            case 6:
                object = findPrices6(shops, product);
                break;

            default:
                object=findPrices1(shops, product);
                break;
        }
        return object;
    }


    public static List<String> findPrices1(List<Shop> shops,String product) {
        return shops.stream()
                .map(shop -> String.format("%s price is %.2f",
                        shop.getShopName(), shop.getPrice(product)))
                .collect(toList());

    }


    public static List<String> findPrices2(List<Shop> shops,String product) {
        return shops.parallelStream()
                .map(shop -> String.format("%s price is %.2f",
                        shop.getShopName(), shop.getPrice(product)))
                .collect(toList());
    }

    public static List<String> findPrices3(List<Shop> shops,String product) {

        return shops.stream()
                .map(shop -> CompletableFuture.supplyAsync(() ->
                        String.format("%s price is %.2f",
                                shop.getShopName(), shop.getPrice(product))))
                .map(CompletableFuture::join)
                .collect(toList());
    }


    /**
     *
     * @param shops
     * @param product
     * @return
     */
    public static List<String> findPrices4(List<Shop> shops,String product) {
        Executor executor = Executors.newFixedThreadPool(128);
        return shops
                .stream()
                .map(shop -> CompletableFuture.supplyAsync(() ->
                        String.format("%s price is %.2f",
                                shop.getShopName(), shop.getPrice(product)), executor)
                )
//                .map(future->future.thenApply())
//                .map(future->future.thenCompose())
                .map(CompletableFuture::join)
                .collect(toList());


    }
    /**
     *
     * @param shops
     * @param product
     * @return
     */
    public static List<Integer> findPrices5(List<Shop> shops, String product) {
        ExecutorService executor = Executors.newFixedThreadPool(16);
        List<CompletableFuture<Integer>> priceFutures =
                shops.stream()
                        .map(
                                shop -> CompletableFuture.supplyAsync(() ->
                                        String.format("%s price is %.2f", shop.getShopName(), shop.getPrice(product)), executor))
                        .map(future -> future.thenApply(String::hashCode))
                        .collect(toList());
        executor.shutdown();
        return priceFutures.stream().map(CompletableFuture::join).collect(toList());
    }


    /**
     * 在指定的线程池中执行CompletableFuture 的方式
     *
     * @param shops
     * @param product
     * @return
     */
    public static List<Integer> findPrices6(List<Shop> shops, String product) {
        ExecutorService executor = Executors.newFixedThreadPool(8);
        List<CompletableFuture<Integer>> priceFutures = shops
                .stream()
                .map(shop -> CompletableFuture.supplyAsync(() -> {
                    String shopName = shop.getShopName();
                    String price = shop.getPrice(product, null);
                    return String.format("%s price is %.2f", shopName, price);
                }, executor))
                .map(future -> future.thenApply(String::hashCode)).collect(toList());
        executor.shutdown();
        return priceFutures.stream().map(CompletableFuture::join).collect(toList());
    }

}
