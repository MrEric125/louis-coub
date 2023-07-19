package com.concurrent;

import java.io.Serializable;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author louis
 * @date 2023/6/6
 */
public class HashMapUtils<K extends Serializable,V extends Serializable> {

    transient volatile Node<K,V>[] table;
    static final int MOVED     = -1;


    final V putVal(K key, V value, boolean onlyIfAbsent) {
        if (key == null || value == null) throw new NullPointerException();
        int hash = spread(key.hashCode());
        int binCount = 0;
        for (Node<K,V>[] tab = table;;) {
            Node<K,V> f;
            int n, i, fh;
            n = tab.length;
            f = tabAt(tab, i = (n - 1) & hash);
            fh = f.hash;
            if (tab == null || n == 0) {
                tab = initTable();
            } else if (f==null) {

            }else if (fh==MOVED)

        }
    }
    static final int spread(int h) {
        return (h ^ (h >>> 16)) &  0x7fffffff;
    }

    static class Node<K,V> implements Map.Entry<K,V> {
        final int hash;
        final K key;
        volatile V val;
        volatile ConcurrentHashMap.Node<K,V> next;

        Node(int hash, K key, V val, ConcurrentHashMap.Node<K,V> next) {
            this.hash = hash;
            this.key = key;
            this.val = val;
            this.next = next;
        }

        public final K getKey()       { return key; }
        public final V getValue()     { return val; }
        public final int hashCode()   { return key.hashCode() ^ val.hashCode(); }
        public final String toString(){ return key + "=" + val; }
        public final V setValue(V value) {
            throw new UnsupportedOperationException();
        }

        public final boolean equals(Object o) {
            Object k, v, u; Map.Entry<?,?> e;
            return ((o instanceof Map.Entry) &&
                    (k = (e = (Map.Entry<?,?>)o).getKey()) != null &&
                    (v = e.getValue()) != null &&
                    (k == key || k.equals(key)) &&
                    (v == (u = val) || v.equals(u)));
        }

        /**
         * Virtualized support for map.get(); overridden in subclasses.
         */
        ConcurrentHashMap.Node<K,V> find(int h, Object k) {
            ConcurrentHashMap.Node<K,V> e = this;
            if (k != null) {
                do {
                    K ek;
                    if (e.hash == h &&
                            ((ek = e.key) == k || (ek != null && k.equals(ek))))
                        return e;
                } while ((e = e.next) != null);
            }
            return null;
        }
    }
}
