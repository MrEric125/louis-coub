package com.louis.coub.escustomer.collect;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

public class DmcThreadFactory implements ThreadFactory {
    private static final AtomicInteger poolNumber = new AtomicInteger(1);
    private final ThreadGroup group;
    private final AtomicInteger threadNumber = new AtomicInteger(1);
    private final String namePrefix;
    private final boolean isDaemon;

    public DmcThreadFactory(String factoryName,boolean isDaemon) {
        SecurityManager s = System.getSecurityManager();
        group = (s != null) ? s.getThreadGroup() :
                Thread.currentThread().getThreadGroup();
        namePrefix = factoryName + "-pool-" + poolNumber.getAndIncrement() + "-thread-";
        this.isDaemon = isDaemon;
    }

    @Override
    public Thread newThread(Runnable r) {
        Thread t = new Thread(group, r,namePrefix + threadNumber.getAndIncrement(),0);
        t.setDaemon(isDaemon);
        return t;
    }
}