package com.louis.longagocode.concurrent;

import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author Louis
 * @date created on 2021/3/7
 * description:
 */
@Slf4j
public class CountExample {

    private static final int threadNum = 200;

    private static final int clientNum = 100000;

    private static long count = 0;

    private static AtomicLong atomicCount = new AtomicLong(0);

    public static final Map<Integer, Integer> map = Maps.newHashMap();
    public static final Map<Integer, Integer> map2 = Maps.newConcurrentMap();


    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        final Semaphore semaphore = new Semaphore(threadNum);
        for (int i = 0; i < clientNum; i++) {
            final int num = i;
            executorService.execute(() -> {
                try {
                    semaphore.acquire();
                    addByMap(num);
                    addByConcurrentMap(num);
                    add();
                    atomicLong();
                    semaphore.release();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
        executorService.shutdown();
        log.info("count by long：{}", count);
        log.info("count by map:{}", map.size());
        log.info("count by concurrent map:{}", map2.size());
        log.info("count by atomic long:{}", atomicCount.get());

    }

    public static void add() {
        count++;
    }

    public static void addByMap(int num) {
        map.put(num, num);
    }

    public static void addByConcurrentMap(int num) {
        map2.put(num, num);
    }

    public static void atomicLong() {
        atomicCount.incrementAndGet();
    }
}
