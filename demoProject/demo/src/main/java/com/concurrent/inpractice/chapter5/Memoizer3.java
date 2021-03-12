package com.concurrent.inpractice.chapter5;

import com.google.common.collect.Maps;

import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

public class Memoizer3 <A,V> implements Computable<A,V>{

    private final Map<A, Future<V>> cache = Maps.newConcurrentMap();

    private final Computable<A, V> avComputable;

    public Memoizer3(Computable<A, V> avComputable) {
        this.avComputable = avComputable;
    }

    @Override
    public V compute(A arg) throws InterruptedException {
        Future<V> f = cache.get(arg);
        if (f==null) {
            FutureTask<V> futureTask = new FutureTask<>(() -> avComputable.compute(arg));

            f = futureTask;

            cache.put(arg, futureTask);

            futureTask.run();
        }
        try {
            return f.get();
        } catch (Exception e) {
            throw new InterruptedException(e.getMessage());
        }

    }
}
