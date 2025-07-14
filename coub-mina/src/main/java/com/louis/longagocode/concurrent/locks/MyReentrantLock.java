package com.louis.longagocode.concurrent.locks;

import java.io.Serializable;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * @author jun.liu
 * @since 2021/5/25 9:54
 */
public class MyReentrantLock implements Lock, Serializable {

    abstract static class Sync extends AbstractQueuedSynchronizer{

        abstract void lock();

        /**
         *  通过exclusiveOwnerThread 和state 来决定操作是否独占，
         * @param acquires
         * @return
         */
        final boolean nonfairTryAcquire(int acquires){
            final Thread current = Thread.currentThread();
            int c = getState();
            if (c==0) {
                if (compareAndSetState(0,acquires)) {
                    setExclusiveOwnerThread(current);
                    return true;
                }
            // 如果当前线程不为独占线程，并且状态bu等于0的情况，说明当前线程获取锁失败
            }else if (current==getExclusiveOwnerThread()){
                int nextc = c + acquires;
                if (nextc < 0) {
                    throw new Error("ee");
                }
                setState(nextc);
                return true;
            }
            return false;
        }

    }
    @Override
    public void lock() {

    }

    @Override
    public void lockInterruptibly() throws InterruptedException {

    }

    @Override
    public boolean tryLock() {
        return false;
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return false;
    }

    @Override
    public void unlock() {

    }

    @Override
    public Condition newCondition() {
        return null;
    }
}
