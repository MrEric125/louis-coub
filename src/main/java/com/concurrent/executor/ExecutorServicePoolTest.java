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

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.submit(new Runnable() {
            @Override
            public void run() {


            }
        });
    }
}
