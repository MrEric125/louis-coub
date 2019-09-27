package com.concurrent.locks;

import com.google.common.collect.Lists;

import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author louis
 * <p>
 * Date: 2019/9/5
 * Description:
 */
public class ReentrantLockTest {

    public static void main(String[] args) {
        ReentrantLock lock = new ReentrantLock();
        List<Thread> threads = Lists.newArrayList();
        for (int i = 0; i <3; i++) {
            Thread thread = new Thread(() -> {
                lock.lock();
                try {
                    System.out.println("thread name==>" + Thread.currentThread().getName());
                    Thread.sleep(60*1000);
                } catch (Exception e) {
                   e.printStackTrace();
                }finally {
                    lock.unlock();
                }
            }, "thread" + i);
            threads.add(thread);

        }
        for (Thread thread : threads) {
            thread.start();
        }

    }
}
