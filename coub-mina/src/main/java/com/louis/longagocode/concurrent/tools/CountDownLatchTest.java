package com.louis.longagocode.concurrent.tools;


import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author louis
 * <p>
 * Date: 2019/8/20
 * Description:
 */
public class CountDownLatchTest {

    private static final int N = 5000;
    private static final int Num = 10;

    public static void main(String[] args) {
        CountDownLatch countDownLatch = new CountDownLatch(Num);
        ExecutorService executorService = Executors.newCachedThreadPool();
        int processors = Runtime.getRuntime().availableProcessors();
        System.out.println(processors);


    }
}
