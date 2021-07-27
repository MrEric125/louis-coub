package com.concurrent.inpractice.chapter5;

public interface Computable<A,V> {

    V compute(A arg) throws InterruptedException;

}
