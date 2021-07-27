package com.concurrent.executor;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author louis
 * <p>
 * Date: 2019/12/20
 * Description:
 */
public class DefaultMKThreadFactory  implements ThreadFactory {
    private static  AtomicInteger poolNumber ;
    private final ThreadGroup group;
    private final AtomicInteger threadNumber = new AtomicInteger(1);
    private final String namePrefix;

    public DefaultMKThreadFactory() {
        int v = (int) (Math.random() * 10);
        poolNumber = new AtomicInteger(v);
        SecurityManager s = System.getSecurityManager();
        this.group = ((s != null) ? s.getThreadGroup() : Thread.currentThread()
                .getThreadGroup());
        this.namePrefix = "mk-pool-" + poolNumber.getAndIncrement()
                + "-thread-";
    }

    public DefaultMKThreadFactory(String prefix) {
        int v = (int) (Math.random() * 10);
        poolNumber = new AtomicInteger(v);
        SecurityManager s = System.getSecurityManager();
        this.group = ((s != null) ? s.getThreadGroup() : Thread.currentThread()
                .getThreadGroup());
        this.namePrefix = "mk-pool-" + prefix + "-"
                + poolNumber.getAndIncrement() + "-thread-";
    }

    public DefaultMKThreadFactory(String sysCode, String prefix) {
        int v = (int) (Math.random() * 10);
        poolNumber = new AtomicInteger(v);
        SecurityManager s = System.getSecurityManager();
        this.group = ((s != null) ? s.getThreadGroup() : Thread.currentThread()
                .getThreadGroup());
        this.namePrefix = DefaultMKThreadFactory
                .join(new String[] { sysCode, prefix,
                                String.valueOf(poolNumber.getAndIncrement()), "thread" },
                        "-");
    }

    public Thread newThread(Runnable r) {
        Thread t = new Thread(this.group, r, this.namePrefix
                + this.threadNumber.getAndIncrement(), 0L);
        if (t.isDaemon())
            t.setDaemon(false);
        if (t.getPriority() != 5)
            t.setPriority(5);
        return t;
    }
    public static String join(String[] pieces, String separator) {
        return join(Arrays.asList(pieces), separator);
    }

    private static String join(List<String> pieces, String separator) {
        StringBuilder buffer = new StringBuilder();
        Iterator iter = pieces.iterator();

        while(iter.hasNext()) {
            buffer.append((String)iter.next());
            if (iter.hasNext()) {
                buffer.append(separator);
            }
        }

        return buffer.toString();
    }

    public static void main(String[] args) {
        int v = (int) (Math.random() * 10);
        AtomicInteger atomicInteger = new AtomicInteger(v);
        System.out.println(atomicInteger);
    }
}
