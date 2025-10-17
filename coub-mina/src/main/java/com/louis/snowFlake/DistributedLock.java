package com.louis.snowFlake;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.*;

/**
 * 适用场景
 * 执行时间不确定的业务逻辑
 *
 * 长时间运行的批处理任务
 *
 * 对数据一致性要求高的关键业务
 *
 * 不适用场景
 * 短时间锁定（可以使用固定过期时间）
 *
 * 高性能要求的场景（避免额外开销）
 *
 * 客户端可能频繁崩溃的环境
 */
@Component
public class DistributedLock {
    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Autowired
    private DistributedLock distributedLock;

    /**
     * 加锁 - 使用SET NX EX命令保证原子性
     * @param lockKey 锁的key
     * @param requestId 请求的id
     * @param expireSeconds 过期时间
     */
    public boolean tryLock(String lockKey, String requestId, long expireSeconds) {
        return Boolean.TRUE.equals(
                redisTemplate.opsForValue().setIfAbsent(
                        lockKey,
                        requestId,
                        expireSeconds,
                        TimeUnit.SECONDS
                )
        );
    }

    /**
     * 释放锁 - 使用Lua脚本保证原子性
     */
    public boolean releaseLock(String lockKey, String requestId) {
        String luaScript =
                "if redis.call('get', KEYS[1]) == ARGV[1] then " +
                        "   return redis.call('del', KEYS[1]) " +
                        "else " +
                        "   return 0 " +
                        "end";

        return executelua(luaScript, lockKey, requestId, null);

    }
//    @Autowired
//    private RedisTemplate<String, String> redisTemplate;

    private final ScheduledExecutorService watchdogExecutor =
            Executors.newScheduledThreadPool(10);

    /**
     * 锁续期任务
     */
    private final Map<String, ScheduledFuture<?>> renewalTasks = new ConcurrentHashMap<>();

    /**
     * 获取锁（带看门狗）
     */
    public boolean tryLockWithWatchdog(String lockKey, String requestId,
                                       long expireSeconds, long watchdogInterval) {
        // 获取锁
        Boolean success = redisTemplate.opsForValue()
                .setIfAbsent(lockKey, requestId, expireSeconds, TimeUnit.SECONDS);

        if (Boolean.TRUE.equals(success)) {
            // 启动看门狗
            startWatchdog(lockKey, requestId, expireSeconds, watchdogInterval);
            return true;
        }
        return false;
    }

    /**
     * 启动看门狗任务
     */
    private void startWatchdog(String lockKey, String requestId,
                               long expireSeconds, long interval) {
        ScheduledFuture<?> future = watchdogExecutor.scheduleAtFixedRate(() -> {
            try {
                // 检查锁是否还被当前线程持有
                String currentValue = redisTemplate.opsForValue().get(lockKey);
                if (requestId.equals(currentValue)) {
                    // 续期
                    redisTemplate.expire(lockKey, expireSeconds, TimeUnit.SECONDS);
                    System.out.println("看门狗续期成功: " + lockKey);
                } else {
                    // 锁已被其他线程获取，停止看门狗
                    stopWatchdog(lockKey,requestId);
                }
            } catch (Exception e) {
                System.err.println("看门狗续期失败: " + e.getMessage());
                stopWatchdog(lockKey,requestId);
            }
        }, interval / 3, interval / 3, TimeUnit.SECONDS); // 每间隔1/3过期时间执行一次

        renewalTasks.put(lockKey + ":" + requestId, future);
    }


    /**
     * 停止看门狗任务
     */
    private void stopWatchdog(String lockKey, String requestId) {
        String taskKey = lockKey + ":" + requestId;
        ScheduledFuture<?> future = renewalTasks.remove(taskKey);
        if (future != null) {
            future.cancel(false);
        }
    }

    /**
     * 执行Lua脚本
     */
    private boolean executelua(String luaScript, String lockKey, String requestId,Long expireSeconds) {
        DefaultRedisScript<Long> redisScript = new DefaultRedisScript<>();
        redisScript.setScriptText(luaScript);
        redisScript.setResultType(Long.class);

        Long result = redisTemplate.execute(redisScript,
                Collections.singletonList(lockKey), requestId, expireSeconds);

        return result != null && result == 1;
    }

    // 使用示例
    public void businessMethod() {
        String lockKey = "order:lock:123";
        String requestId = UUID.randomUUID().toString();

        try {
            if (distributedLock.tryLock(lockKey, requestId, 30)) {
                // 执行业务逻辑
//                processOrder();
            } else {
                throw new RuntimeException("获取锁失败");
            }
        } finally {
            distributedLock.releaseLock(lockKey, requestId);
        }
    }

}
