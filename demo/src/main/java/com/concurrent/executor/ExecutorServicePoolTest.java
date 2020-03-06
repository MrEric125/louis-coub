package com.concurrent.executor;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author louis
 * <p>
 * Date: 2019/9/2
 * Description:
 */
public class ExecutorServicePoolTest {

    static ExecutorService executorService = Executors.newCachedThreadPool();


    public static void main(String[] args) {

        for (int i = 0; i < 10; i++) {
            executorService.submit(() -> System.out.println("ttt"));
        }
        executorService.shutdown();
    }
}
