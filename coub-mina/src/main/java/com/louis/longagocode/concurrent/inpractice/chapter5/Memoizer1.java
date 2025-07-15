package com.louis.longagocode.concurrent.inpractice.chapter5;



import java.util.HashMap;
import java.util.Map;

public class Memoizer1 <A,V> implements Computable<A,V> {

    private final Map<A, V> cache = new HashMap<A, V>();

    private final Computable<A, V> avComputable;

    public Memoizer1(Computable<A, V> avComputable) {
        this.avComputable = avComputable;
    }

    @Override
    public V compute(A arg) throws InterruptedException {
        V result = cache.get(arg);
        if (result!=null) {
            result =avComputable.compute(arg);
            cache.put(arg,result);
        }
        return result;
    }
}
