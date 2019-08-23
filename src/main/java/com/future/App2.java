package com.future;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static java.util.stream.Collectors.toList;

/**
 * @author louis
 * <p>
 * Date: 2019/8/23
 * Description:
 */
public class App2 {

    public static List<Integer> findPrices5(List<Shop> shops, String product) {
        ExecutorService executor = Executors.newFixedThreadPool(8);
        List<CompletableFuture<Integer>> priceFutures = shops.stream().map(shop -> CompletableFuture.supplyAsync(() ->
                String.format("%s price is %.2f",
                        shop.getShopName(), shop.getPrice(product, null)), executor)
        ).map(future -> future.thenApply(String::hashCode)).collect(toList());
        executor.shutdown();
        return priceFutures.stream().map(CompletableFuture::join).collect(toList());


    }


}
