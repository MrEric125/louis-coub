package com.concurrent.inpractice.chapter7;

import java.math.BigInteger;
import java.util.concurrent.BlockingQueue;

/**
 * @author jun.liu
 * @date created on 2020/10/26
 * description:
 */
public class BrokenPrimeProducer extends Thread {

    private final BlockingQueue<BigInteger> queue;

    private volatile boolean cancelled = false;

    public BrokenPrimeProducer(BlockingQueue<BigInteger> queue) {
        this.queue = queue;
    }
    public void run() {
        try {
            BigInteger p = BigInteger.ONE;
            while (!cancelled) {
                queue.put(p = p.nextProbablePrime());

            }
        } catch (InterruptedException e) {

        }
    }

    public void cancel() {
        cancelled = true;
    }

    void consumePrimes() throws InterruptedException {
        BlockingQueue<BigInteger> primes = queue;
        BrokenPrimeProducer primeProducer = new BrokenPrimeProducer(primes);
        primeProducer.start();

        try {
            while (needMorePrimes()) {
                consumer(primes.take());
            }
        }finally {
            primeProducer.cancel();
        }
    }

    private boolean needMorePrimes() {
        return false;
    }

    private void consumer(BigInteger take) {
    }
}
