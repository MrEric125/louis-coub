package com.louis.concurrent;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class TokenBucketRateLimiter {
    private final AtomicInteger tokens;
    private final int capacity;
    private final ScheduledExecutorService scheduler;

    public TokenBucketRateLimiter(int capacity, int refillRatePerSecond) {
        this.capacity = capacity;
        this.tokens = new AtomicInteger(capacity);
        this.scheduler = Executors.newScheduledThreadPool(1);
        
        // 每秒补充令牌
        scheduler.scheduleAtFixedRate(() -> {
            if (tokens.get() < capacity) {
                tokens.incrementAndGet();
            }
        }, 0, 1000 / refillRatePerSecond, TimeUnit.MILLISECONDS);
    }

    public boolean tryAcquire() {
        while (true) {
            int current = tokens.get();
            if (current == 0) {
                return false;
            }
            if (tokens.compareAndSet(current, current - 1)) {
                return true;
            }
        }
    }

    public void stop() {
        scheduler.shutdown();
    }


    public static void main(String[] args) {
        TokenBucketRateLimiter limiter = new TokenBucketRateLimiter(10, 5); // 桶容量10，每秒生成5个令牌
        if (limiter.tryAcquire()) {
            // 获取令牌成功
        } else {
            // 令牌不足
        }

    }
}
