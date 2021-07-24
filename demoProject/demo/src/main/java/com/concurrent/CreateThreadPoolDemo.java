package com.concurrent;

import org.junit.Test;

import java.util.concurrent.*;

public class CreateThreadPoolDemo {


    @Test
    public void testThreadPoolExecutor() {

        ThreadPoolExecutor executor = new ThreadPoolExecutor(1,100,100, TimeUnit.SECONDS,new LinkedBlockingDeque<>(100));

        ExecutorService executorService = Executors.newSingleThreadExecutor();




    }
}
