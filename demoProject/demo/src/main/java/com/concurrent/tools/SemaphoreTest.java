package com.concurrent.tools;

import com.future.Shop;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author louis
 * <p>
 * Date: 2019/8/30
 * Description:
 */
@Slf4j
public class SemaphoreTest {
    private static int totalcount = 5000;
    //    private static AtomicInteger sum = new AtomicInteger();
    private static int sum = 0;
    public static void main(String[] args) {
     Semaphore semaphore = new Semaphore(totalcount,true);
        ReentrantLock reentrantLock = new ReentrantLock();
        ExecutorService executorService = Executors.newCachedThreadPool();
         Runnable runnable = () -> {

             try {
//                 semaphore.acquire(2);
                 reentrantLock.lock();
                 log.info("thread-name:{}", Thread.currentThread().getName());
                 sum();
//                 semaphore.release();
             } catch (Exception e) {
                 e.printStackTrace();
             }finally {
                 reentrantLock.unlock();
             }
         };
        for (int i = 0; i < totalcount; i++) {
            executorService.execute(runnable);
        }
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

//        log.info("sum值为：{}", sum.longValue());
        log.info("sum值为：{}", sum);
        executorService.shutdown();
    }

    private static void sum() {
//        sum.getAndIncrement();
        sum++;

    }

}
