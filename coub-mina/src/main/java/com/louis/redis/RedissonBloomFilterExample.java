package com.louis.redis;

import org.redisson.Redisson;
import org.redisson.api.RBloomFilter;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;

public class RedissonBloomFilterExample {
    
    public static void main(String[] args) {
        // 配置 Redisson
        Config config = new Config();
        config.useSingleServer().setAddress("redis://127.0.0.1:6379");
        
        RedissonClient redisson = Redisson.create(config);
        
        // 获取或创建布隆过滤器
        RBloomFilter<String> bloomFilter = redisson.getBloomFilter("user-bloom-filter");
        
        // 初始化布隆过滤器
        // 预期插入数量100000，错误率1%
        bloomFilter.tryInit(100000L, 0.01);
        
        // 添加数据
        for (int i = 0; i < 1000; i++) {
            bloomFilter.add("user:" + i);
        }
        
        // 检查数据是否存在
        System.out.println("Contains user:100: " + bloomFilter.contains("user:100"));
        System.out.println("Contains user:9999: " + bloomFilter.contains("user:9999"));
        
        // 获取统计信息
        System.out.println("Count: " + bloomFilter.count());
        System.out.println("Size: " + bloomFilter.getSize());
        System.out.println("Hash iterations: " + bloomFilter.getHashIterations());
        
        redisson.shutdown();
    }
}