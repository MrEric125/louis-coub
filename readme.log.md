一键将 pom项目升级为 gradle项目
gradle init --type pom

.
root 密码：
!qaz@wsx

mac  授权

window 查看端口被占用
netstat -aon|findstr "8080"
tasklist|findstr "2720"
taskkill 

内核态：操作系统在运行的时候，cpu的一种状态，安全考虑，能够完全控制系统
用户态，不能完全控制系统，
consistent read

/**
* 释放分布式锁
* @param lockKey 锁
* @param requestId 请求标识
* @return 是否释放成功
*/
public boolean releaseDistributedLock(String lockKey, String value) {

    	try {
    		String script = "if redis.call('get', KEYS[1]) == ARGV[1] then return redis.call('del', KEYS[1]) else return 0 end";
            DefaultRedisScript<String> redisScript = new DefaultRedisScript<String>();
    		redisScript.setScriptText(script);
    		redisScript.setResultType(String.class);
    		Object result = redisTemplate.execute(redisScript, Collections.singletonList(lockKey), value);

            if (RELEASE_SUCCESS.equals(result.toString())) {
                return true;
            }
            return false;
		} catch (Exception e) {
			log.error("释放锁（" + lockKey + "）发生异常：{}",e);
			return false;
		}


    }