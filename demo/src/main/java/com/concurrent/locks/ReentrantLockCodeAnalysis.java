package com.concurrent.locks;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author John·Louis
 * @date created on 2020/2/20
 * description:
 */
public class ReentrantLockCodeAnalysis {

    abstract static class Sync extends AbstractQueuedSynchronizer {


    }

    static final class NonfairSync extends Sync {


        /**
         * 通过cas的方式设置state 也就是获取锁成功，则将党建线程设置为独占线程
         * 如果获取锁失败，则进入Acquire方法进行后续处理
         *
         */
        final void lock(){
            if (compareAndSetState(0, 1)) {
                setExclusiveOwnerThread(Thread.currentThread());
            } else {

                acquire(1);

            }
        }
    }





    public static void main(String[] args) {

    }

    public synchronized void syncUser() {
        synchronized (this){

        }
        ReentrantLockCodeAnalysis reentrantLockCodeAnalysis = new ReentrantLockCodeAnalysis();
        synchronized (reentrantLockCodeAnalysis){

        }
        for (int i = 0; i < 100; i++) {
            synchronized (this){
            }
        }
    }

    public void lockUse() {
        ReentrantLock lock = new ReentrantLock();
        lock.lock();
        try {
            lock.tryLock(100, TimeUnit.MICROSECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

}
