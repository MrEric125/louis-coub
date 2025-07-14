package com.louis.longagocode.concurrent.foundation;

import com.concurrent.foundation.WorkerThread;

/**
 * @author John·Louis
 * @date created on 2020/2/2
 * description:
 * 竞态例子
 */
public class RaceConditionDemo {

    public static void main(String[] args) {
        int numberOfThreads = args.length > 0 ? Short.valueOf(args[0]) : Runtime.getRuntime().availableProcessors();
        Thread[] workThreads = new Thread[numberOfThreads];
        for (int i = 0; i < numberOfThreads; i++) {
            workThreads[i] = new WorkerThread(i, 10);
        }
    }
}
