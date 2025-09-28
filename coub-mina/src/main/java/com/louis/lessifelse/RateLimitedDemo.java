package com.louis.lessifelse;


import com.google.common.util.concurrent.RateLimiter;

import java.time.Duration;

public class RateLimitedDemo {


    public static void main(String[] args) {
        // 1s 放 5 个令牌到桶里也就是 0.2s 放 1个令牌到桶里
        RateLimiter rateLimiter = RateLimiter.create(5, Duration.ofMillis(1));
        long start = System.currentTimeMillis();
        System.out.println("开始获取令牌" + start);
        for (int i = 0; i < 10; i++) {
            double sleepingTime = rateLimiter.acquire(1);

            System.out.printf("get 1 tokens: %ss%n", sleepingTime);
        }
        long end = System.currentTimeMillis();

        System.out.println("结束获取令牌"+(end-start));


    }
}
