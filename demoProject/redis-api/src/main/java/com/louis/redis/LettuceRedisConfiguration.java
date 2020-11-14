//package com.louis.redis;
//
//import com.sf.framework.cacheproxy.redis.RedisCache;
//import com.sf.framework.cacheproxy.redis.RedisConfig;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//
///**
// * @author louis
// * <p>
// * Date: 2020/2/27
// * Description:
// */
//@Configuration
//public class LettuceRedisConfiguration {
//
//
//    @Value("${mk.autocache.servers}")
//    private String redisServers;
//
//    @Value("${mk.autocache.password}")
//    private String password;
//
//
//    @Value("${mk.autocache.masters}")
//    private String masters;
//
//    @Bean
//    public RedisCache redisCache() {
//        return new RedisCache(redisConfig());
//    }
//
//
//
//    @Bean
//    public RedisConfig redisConfig() {
//        String realServers = String.join(",", redisServers.split(" "));
//        return new RedisConfig()
//                .setServers(realServers)
//                .setPassword(password)
//                .setMasters(masters)
//                .setTtl(60 * 60*2);
//
//
//    }
//}
