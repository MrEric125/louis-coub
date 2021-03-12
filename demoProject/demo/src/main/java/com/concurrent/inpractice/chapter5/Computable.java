package com.concurrent.inpractice.chapter5;

import java.util.concurrent.ExecutionException;

public interface Computable<A,V> {

    V compute(A arg) throws InterruptedException;

}
