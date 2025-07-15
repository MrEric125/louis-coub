package com.louis.longagocode.concurrent.locks;


import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author louis
 * <p>
 * Date: 2019/9/4
 * Description:
 */
public class App {

    public static void main(String[] args) {
        Lock lock = new ReentrantLock();
        Condition condition = lock.newCondition();

        // 保证两个线程获取的是同一把锁 和 同一个COndition

        new Thread(new ConditionWait(condition, lock)).start();
        new Thread(new ConditionNotify(condition, lock)).start();


    }
}
