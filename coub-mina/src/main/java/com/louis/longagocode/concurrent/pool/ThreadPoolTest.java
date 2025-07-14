package com.louis.longagocode.concurrent.pool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author John·Louis
 * @date created on 2020/3/15
 * description:
 */
public class ThreadPoolTest {

    public static void main(String[] args) {
        ExecutorService service = Executors.newFixedThreadPool(4);
        for (int i = 0; i < 10; i++) {
            int finalI = i;
            service.execute(() -> System.out.println("i=>" + finalI)
            );
        }
    }
}
