package com;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@Slf4j
public class ThreadPoolTest {


    public static void main(String[] args) {

        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(5, 10, 1000,
                TimeUnit.MILLISECONDS, new java.util.concurrent.LinkedBlockingQueue<>(10));

        log.info("corePoolSize: {}", threadPoolExecutor.getCorePoolSize());
        log.info("maximumPoolSize: {}", threadPoolExecutor.getMaximumPoolSize());


        for (int i = 0; i < 100; i++) {
            threadPoolExecutor.execute(() -> {

                System.out.println("=========");
                log.info("activeCount: {}", threadPoolExecutor.getActiveCount());
                log.info("poolSize: {}", threadPoolExecutor.getPoolSize());
                int queueCapacity = threadPoolExecutor.getQueue().size();
                System.out.println("队列长度: " + queueCapacity);
                System.out.println("=========");
            });
        }

    }
}
