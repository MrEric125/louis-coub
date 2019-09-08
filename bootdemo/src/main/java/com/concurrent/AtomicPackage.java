package com.concurrent;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * @author louis
 * <p>
 * Date: 2019/8/20
 * Description:
 */
public class AtomicPackage implements Runnable{

    private ThreadLocal<Integer> num = new ThreadLocal<>();
    AtomicBoolean flag = new AtomicBoolean();
    public static void main(String[] args) {
        AtomicPackage ast = new AtomicPackage();
        Thread thread1 = new Thread(ast,"线程1");
        Thread thread2 = new Thread(ast,"线程2");
        Thread thread3 = new Thread(ast,"线程3");
        thread1.start();
        thread2.start();
        thread3.start();



    }

    @Override
    public void run() {
        Integer currentRetryTime = num.get() == null ? 0 : num.get();


        if (currentRetryTime==5) {
            System.out.println("重试结束");
            return;
        }
        System.out.println("thread:"+Thread.currentThread().getName()+";flag:"+flag.get());

        if (flag.compareAndSet(true,false)){
            System.out.println(Thread.currentThread().getName()+""+flag.get());
            flag.set(true);
        }else{
            System.out.println("重试机制thread:"+Thread.currentThread().getName()+";flag:"+flag.get());
            System.out.println("重试第"+currentRetryTime+"次");
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            num.set(currentRetryTime+1);
            run();
        }
    }
    public void ABA() {
    }
}
