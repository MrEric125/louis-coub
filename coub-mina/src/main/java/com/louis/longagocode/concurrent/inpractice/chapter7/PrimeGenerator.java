package com.louis.longagocode.concurrent.inpractice.chapter7;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;

import javax.annotation.concurrent.ThreadSafe;
import java.math.BigInteger;
import java.util.List;

/**
 * @author jun.liu
 * @date created on 2020/10/26
 * description:
 */
@ThreadSafe
public class PrimeGenerator implements Runnable {


    private final List<BigInteger> primes = Lists.newArrayList();

    private volatile boolean cancelled;

    @Override
    public void run() {
        BigInteger p = BigInteger.ONE;
        while (!cancelled) {
            p = p.nextProbablePrime();
            synchronized (this) {
                primes.add(p);
            }
        }
    }

    public void cancel() {
        cancelled = true;
    }
    public synchronized List<BigInteger> get() {
        return Lists.newArrayList(primes);
    }

    public static List<BigInteger> aSecondOfPrimes() throws InterruptedException {
        PrimeGenerator generator = new PrimeGenerator();
        new Thread(generator).start();
        try {
            Thread.sleep(100);

        }finally {
            generator.cancel();

        }
        return generator.get();
    }

    public static void main(String[] args) throws InterruptedException {
        System.out.println(JSON.toJSONString(aSecondOfPrimes()));
    }
}
