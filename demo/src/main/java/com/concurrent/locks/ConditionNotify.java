package com.concurrent.locks;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * @author louis
 * <p>
 * Date: 2019/9/4
 * Description:
 */
public class ConditionNotify implements Runnable {
    private Condition condition;
    private Lock lock;

    public ConditionNotify(Condition condition, Lock lock) {
        this.condition = condition;
        this.lock = lock;
    }

    @Override
    public void run() {
        try {
            lock.lock();

            System.out.println("begin - notify");
            condition.signal();
            System.out.println("end - notify");

        }catch (Exception e){

        }finally {
            lock.unlock();
        }

    }
}
