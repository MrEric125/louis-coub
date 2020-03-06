//package com.louis.redis;
//
//import com.sf.framework.cacheproxy.CPException;
//import com.sf.framework.cacheproxy.redis.*;
//import io.lettuce.core.cluster.api.sync.RedisClusterCommands;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import org.springframework.util.ReflectionUtils;
//
//import java.lang.reflect.Method;
//import java.util.*;
//
///**
// * @author louis
// * <p>
// * Date: 2020/3/2
// * Description:
// */
//@Service
//public class WmoRedisCache  {
//
//    @Autowired
//    private RedisCache redisCache;
//
//
//    /**
//     * 通过反射的方式获取 RedisCache中的RedisClusterCommands对象
//     * @param pattern
//     * @return
//     */
//    @SuppressWarnings("unchecked")
//    private RedisClusterCommands<String, byte[]> wmoCommands(String pattern) {
//        Method method=ReflectionUtils.findMethod(redisCache.getClass(), "commands",String.class);
//       return Optional.ofNullable(method).map(m -> {
//            ReflectionUtils.makeAccessible(method);
//            Object obj = ReflectionUtils.invokeMethod(method, redisCache, pattern);
//            return (RedisClusterCommands) Optional.ofNullable(obj).orElse(null);
//        }).orElse(null);
//
//
//    }
//
//    public Set<String> keys(String pattern) {
//        checkKey(pattern);
//        List<String> keys = Optional
//                .ofNullable(wmoCommands(pattern))
//                .map(commands -> commands.keys(pattern))
//                .orElse(new ArrayList<>());
//
//        return new HashSet<>(keys);
//    }
//
//    /**
//     *
//     * @param key
//     * @return
//     */
//    public Object getIgnoreType(String key) {
//        String type = redisCache.type(key);
//
//        if (type.equals("hash")) {
//            return redisCache.hgetAll(key);
//        } else if (type.equals("set")) {
//            return redisCache.smembers(key);
//
//        } else if (type.equals("list")) {
//            return redisCache.members(key);
//        } else {
//            return redisCache.get(key);
//        }
//    }
//
//
//
//
//    private void checkKey(String key) {
//        this.assertIfNull(key, "key is null");
//    }
//    private void assertIfNull(Object obj, String msg) {
//        if (obj == null) {
//            throw new CPException(msg);
//        }
//    }
//
//
//
//
//}
