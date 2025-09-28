package com.louis.lessifelse;

import lombok.extern.slf4j.Slf4j;
import org.assertj.core.util.Lists;
import org.redisson.Redisson;
import org.redisson.api.RRateLimiter;
import org.redisson.api.RateIntervalUnit;
import org.redisson.api.RateType;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Slf4j
public class RedisRateLimitedDemo {


    public static void main(String[] args) {


        Config config = new Config();
        config.useSingleServer().setAddress("redis://localhost:6380");
//                .setNodeAddresses(Lists.newArrayList("redis://localhost:6380",
//                        "redis://localhost:6381",
//                        "redis://localhost:6382",
//                        "redis://localhost:6383",
//                        "redis://localhost:6384",
//                        "redis://localhost:6385"));

        // 创建一个 Redisson 客户端实例
        RedissonClient redissonClient = Redisson.create(config);
// 获取一个名为 "javaguide.limiter" 的限流器对象
        RRateLimiter rateLimiter = redissonClient.getRateLimiter("javaguide.limiter");
// 尝试设置限流器的速率为每小时 100 次
// RateType 有两种，OVERALL是全局限流,ER_CLIENT是单Client限流（可以认为就是单机限流）
        rateLimiter.trySetRate(RateType.OVERALL, 100, 1, RateIntervalUnit.HOURS);

        // 获取一个许可，如果超过限流器的速率则会等待
// acquire()是同步方法，对应的异步方法：acquireAsync()
//        rateLimiter.acquire(4);
// 尝试在 5 秒内获取一个许可，如果成功则返回 true，否则返回 false
// tryAcquire()是同步方法，对应的异步方法：tryAcquireAsync()
        boolean res = rateLimiter.tryAcquire(4, 5, TimeUnit.SECONDS);


        log.info("tryAcquire: {}", res);

    }
}
