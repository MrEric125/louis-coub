package com.concurrent;

import com.future.Shop;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author louis
 * <p>
 * Date: 2019/8/30
 * Description:
 */
@Slf4j
public class SemaphoreTest {
    private static int totalcount = 1000;
    private static AtomicInteger sum = new AtomicInteger();
    public static void main(String[] args) {
     Semaphore semaphore = new Semaphore(totalcount,true);
        ExecutorService executorService = Executors.newCachedThreadPool();
         Runnable runnable = () -> {
             try {
                 semaphore.acquire(2);
                 log.info("thread-name:{}", Thread.currentThread().getName());
                 sum();
                 semaphore.release();
             } catch (InterruptedException e) {
                 e.printStackTrace();
             }


         };
        for (int i = 0; i < totalcount; i++) {
            executorService.submit(runnable);
        }
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("sum值为：{}", sum.longValue());
        executorService.shutdown();
    }

    private static void sum() {
        sum.getAndIncrement();



    }

}
