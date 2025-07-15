package com.louis.longagocode.concurrent.locks;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author louis
 * <p>
 * Date: 2019/9/4
 * Description:
 */
public class ReentrantReadWriteLockTest {


    private Object object;

    ReentrantReadWriteLock rrwl = new ReentrantReadWriteLock();

    volatile boolean cacheValid;

    public void process() {

        rrwl.readLock().lock();

        if (!cacheValid) {
            rrwl.readLock().unlock();
            rrwl.writeLock().lock();
            if (!cacheValid) {
                object = writeSomething();
                cacheValid = true;
            }
            rrwl.readLock().lock();
            rrwl.writeLock().unlock();
        }
        userDate(object);
        rrwl.readLock().unlock();

    }

    private Object writeSomething() {
        return null;
    }

    private void userDate(Object object) {

    }
}
