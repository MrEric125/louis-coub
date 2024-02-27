package com.concurrent;

import com.concurrent.tools.ThreadPoolExecutorLouis;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.concurrent.*;

public class CreateThreadPoolDemo {

    private static final int COUNT_BITS = Integer.SIZE - 3;
    private static final int CAPACITY   = (1 << COUNT_BITS) - 1;


    @Test
    public void testThreadPoolExecutor() throws InterruptedException {

        ThreadPoolExecutorLouis executor = new ThreadPoolExecutorLouis(1,100,100, TimeUnit.SECONDS,new LinkedBlockingDeque<>(1));

        for (int i = 0; i < 1; i++) {
            executor.execute(()->{
                System.out.println("threadName:" + Thread.currentThread().getName());
            });
        }

        retry:
        for (;;) {
            for (int i = 0; i < COUNT_BITS; i++) {
                System.out.println(i);

                if (i == 4) {
                    break retry;
                }
                if (i == 8) {
                    continue retry;
                }
            }
        }
        System.out.println("threadName:" + Thread.currentThread().getName());

//        while (true){
//            try {
//                Thread.sleep(100);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
        BlockingQueue<Runnable> workQueue = new LinkedBlockingDeque<>();
        System.out.println("startThreadPoolExecutor");
    }
}
