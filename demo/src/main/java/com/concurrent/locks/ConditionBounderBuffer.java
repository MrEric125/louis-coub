package com.concurrent.locks;

import javax.annotation.concurrent.GuardedBy;
import javax.annotation.concurrent.ThreadSafe;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author louis
 * <p>
 * Date: 2019/9/4
 * Description:
 */
@ThreadSafe
public class ConditionBounderBuffer<T> {
    protected final Lock lock = new ReentrantLock();
    private final Condition notFull = lock.newCondition();
    private final Condition notEmpty = lock.newCondition();
    @GuardedBy("lock")
    @SuppressWarnings("unchecked")
    private final T[] items = (T[]) new Object[10];
    @GuardedBy("lock")
    private int tail,head, count;
//    阻塞直到notFull
    public void put(T t){
        lock.lock();
        try {
            while (count == items.length) {
                notFull.await();
            }
            items[tail] = t;
            if (++tail==items.length) {
                tail = 0;
            }
            ++count;
            notEmpty.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            lock.unlock();

        }

    }
    public T take() {
        lock.lock();
        T t = null;
        try {
            while (count == 0) {
                notEmpty.await();
            }
             t = items[head];
            items[head] = null;
            if (++head==items.length) {
                head = 0;
            }
            --count;
            notFull.signal();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            lock.unlock();

        }
        return t;
    }

}
