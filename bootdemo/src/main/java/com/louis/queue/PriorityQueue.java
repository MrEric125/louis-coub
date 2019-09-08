package com.louis.queue;

import com.louis.future.Shop;

import java.util.Arrays;
import java.util.Comparator;
import java.util.concurrent.PriorityBlockingQueue;

/**
 * @author louis
 * <p>
 * Date: 2019/8/28
 * Description:
 */
public class PriorityQueue {

    public static void main(String[] args) {
        PriorityBlockingQueue<Shop> priorityQueue =
                new PriorityBlockingQueue<>(11, Comparator.comparing(Shop::getShopName));
        priorityQueue.add(new Shop("adavi"));
        priorityQueue.add(new Shop("tashka"));
        priorityQueue.add(new Shop("fafusa"));
//        priorityQueue.stream().map(Shop::getShopName).forEach(System.out::println);
        Shop[] objects = new Shop[priorityQueue.size()];
        Shop[] shops = priorityQueue.toArray(objects);
        Arrays.stream(shops).map(Shop::getShopName).forEach(System.out::println);


    }
}
