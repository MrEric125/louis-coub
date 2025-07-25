package com.louis.longagocode.concurrent.inpractice.chapter5;

import java.util.concurrent.CountDownLatch;

public class TestHarness {

    public long timeTasks(int nThreads, final Runnable task) throws InterruptedException {
        final CountDownLatch startGate = new CountDownLatch(1);
        final CountDownLatch endGate = new CountDownLatch(nThreads);

        for (int i = 0; i < nThreads; i++) {
            Thread thread = new Thread(() -> {
                try {
                    startGate.await();
                    try {
                        task.run();

                    } finally {
                        endGate.countDown();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
            thread.start();

        }
        long start = System.nanoTime();
        startGate.countDown();
        endGate.await();
        long end = System.nanoTime();
        return end - start;

    }

}
