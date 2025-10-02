package com.louis.concurrent;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 滑动窗口算法
 * @author Louis
 * @description
 *
 */
public class SlidingWindowRateLimiter {
    /**
     * 存储每个用户的请求时间戳
     */
    private final ConcurrentHashMap<String, Queue<Long>> windows = new ConcurrentHashMap<>();
    /**
     * 窗口大小，单位毫秒
     */
    private final long windowSizeInMillis;
    /**
     * 最大请求数
     */
    private final int maxRequests;

    public SlidingWindowRateLimiter(long windowSizeInMillis, int maxRequests) {
        this.windowSizeInMillis = windowSizeInMillis;
        this.maxRequests = maxRequests;
    }

    public synchronized boolean allowRequest(String key) {
        long currentTime = System.currentTimeMillis();
        Queue<Long> window = windows.computeIfAbsent(key, k -> new ArrayDeque<>());
        
        // 移除过期的时间戳
        while (!window.isEmpty() && currentTime - window.peek() > windowSizeInMillis) {
            System.out.println("移除过期请求，当前时间：" + currentTime + ", 过期请求时间：" + window.peek());
            window.poll();
        }
        
        if (window.size() < maxRequests) {
            window.offer(currentTime);
            return true;
        }

        return false;
    }

    public static void main(String[] args) {
        SlidingWindowRateLimiter limiter = new SlidingWindowRateLimiter(1, 10); // 1秒内最多10次请求

        long start = System.currentTimeMillis();
        for (int i = 0; i < 20; i++){
            long handleTime = System.currentTimeMillis();
            if (limiter.allowRequest("user1")) {

                // 处理请求
                System.out.println("处理请求" + (handleTime - start));
            } else {
                // 限流

                System.out.println("限流" + (handleTime - start));
            }
        }


    }
}
