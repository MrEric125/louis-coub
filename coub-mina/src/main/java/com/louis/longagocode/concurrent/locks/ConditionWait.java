package com.louis.longagocode.concurrent.locks;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * @author louis
 * <p>
 * Date: 2019/9/4
 * Description:
 */
public class ConditionWait implements Runnable {

    private Condition condition;
    private Lock lock;

    public ConditionWait(Condition condition, Lock lock) {
        this.condition = condition;
        this.lock = lock;
    }


    @Override
    public void run() {
        try {
            lock.lock();

            System.out.println("begin - wait");
            condition.await();
            System.out.println("end - wait");

        } catch (InterruptedException e) {

        } finally {
            lock.unlock();
        }

    }
}
