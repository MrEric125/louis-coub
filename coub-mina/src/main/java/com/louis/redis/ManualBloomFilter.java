package com.louis.redis;

import redis.clients.jedis.Jedis;
import java.util.List;
import java.util.ArrayList;

/**
 * 手动实现的布隆过滤器
 * 使用Jedis操作Redis的位图
 * 适用于需要自定义哈希函数和参数的场景
 *
 * @author Louis
 * @date 2024-06-27
 */
public class ManualBloomFilter {
    private Jedis jedis;
    private String key;
    private int bitSize;
    private int hashFunctions;
    
    public ManualBloomFilter(Jedis jedis, String key, int expectedInsertions, double falseProbability) {
        this.jedis = jedis;
        this.key = key;
        this.bitSize = calculateBitSize(expectedInsertions, falseProbability);
        this.hashFunctions = calculateHashFunctions(bitSize, expectedInsertions);
    }
    
    private int calculateBitSize(int n, double p) {
        return (int) (-n * Math.log(p) / (Math.log(2) * Math.log(2)));
    }
    
    private int calculateHashFunctions(int m, int n) {
        return Math.max(1, (int) Math.round((double) m / n * Math.log(2)));
    }
    
    private int[] getHashPositions(String element) {
        int[] positions = new int[hashFunctions];
        
        // 使用多个哈希函数（这里用简单的方法模拟）
        int hash1 = element.hashCode();
        int hash2 = hash1 ^ (hash1 >>> 16);
        
        for (int i = 0; i < hashFunctions; i++) {
            positions[i] = Math.abs((hash1 + i * hash2) % bitSize);
        }
        
        return positions;
    }
    
    public void add(String element) {
        int[] positions = getHashPositions(element);
        for (int pos : positions) {
            jedis.setbit(key, pos, true);
        }
    }
    
    public boolean exists(String element) {
        int[] positions = getHashPositions(element);
        for (int pos : positions) {
            if (!jedis.getbit(key, pos)) {
                return false;
            }
        }
        return true;
    }
    
    public long getBitCount() {
        return jedis.bitcount(key);
    }
}