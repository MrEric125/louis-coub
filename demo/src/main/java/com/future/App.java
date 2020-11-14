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
//        Shop shop = new Shop("BestShop");
//        long start = System.nanoTime();
//        Future<Double> futurePrice = shop.getPriceAsync2("my favorite product");
//        long invocationTime = ((System.nanoTime() - start) / 1_000_000);
//        System.out.println("Invocation returned after " + invocationTime
//                + " msecs");
//        try {
//            double price = futurePrice.get();
//            System.out.printf("Price is %.2f%n", price);
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//        long retrievalTime = ((System.nanoTime() - start) / 1_000_000);
//        System.out.println("Price returned after " + retrievalTime + " msecs");
    }



    /**
     *
     */
    public static void run2() {
//        List<Shop> shops = Shop.creatShop(null);
//        long start = System.nanoTime();
//        System.out.println(runStratge(shops, "myPhone27S", 3));
//        long duration = (System.nanoTime() - start) / 1_000_000;
//        System.out.println("Done in " + duration + " msecs");
    }


    public static void run3() {
//        List<Shop> shops = Shop.creatShop(null);
//        long start = System.nanoTime();
//        System.out.println(runStratge(shops, "myPhone27S", 5));
//        long duration = (System.nanoTime() - start) / 1_000_000;
//        System.out.println("Done in " + duration + " msecs");
    }

}
