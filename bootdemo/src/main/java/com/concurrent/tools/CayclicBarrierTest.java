package com.concurrent.tools;

import com.google.common.collect.Lists;

import java.util.List;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @author louis
 * <p>
 * Date: 2019/9/24
 * Description:
 * 源码中的案例
  */
public class CayclicBarrierTest {

    private int N;

    private float[][] data;
    private CyclicBarrier barrier;

    class Worker implements Runnable {

        int myRow;

        public Worker(int myRow) {
            this.myRow = myRow;
        }

        @Override
        public void run() {
            while (!done()) {
                processRow(myRow);
                try {
                    barrier.await();
                } catch (InterruptedException | BrokenBarrierException e) {
                    e.printStackTrace();
                    return;
                }
            }
        }

        private void processRow(int myRow) {
        }

        private boolean done() {
            return false;
        }
    }

    public CayclicBarrierTest(float[][] matrix) throws InterruptedException {
        N = matrix.length;
        data = matrix;
        Runnable barrierAction = new Runnable() {
            @Override
            public void run() {

            }
        };
        barrier = new CyclicBarrier(N, barrierAction);
        List<Thread> threadList = Lists.newArrayList();
        for (int i = 0; i < N; i++) {
            Thread thread = new Thread(new Worker(i));
            threadList.add(thread);
            thread.start();
        }
        for (Thread thread : threadList) {
            thread.join();

        }
    }
}
