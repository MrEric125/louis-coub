package com.config;

import io.lettuce.core.RedisClient;
import io.lettuce.core.RedisURI;
import io.lettuce.core.SetArgs;
import io.lettuce.core.api.StatefulRedisConnection;
import io.lettuce.core.api.sync.RedisCommands;
import org.springframework.http.server.reactive.HttpHandler;
import org.springframework.http.server.reactive.ReactorHttpHandlerAdapter;

/**
 * @author louis
 * <p>
 * Date: 2019/10/17
 * Description:
 */
public class WebFluxTest {

    HttpHandler handler = null;

    ReactorHttpHandlerAdapter adapter = new ReactorHttpHandlerAdapter(handler);

//    public static void main(String[] args) {
//        RedisURI uri = RedisURI.builder().withHost("localhost").withPort(32102).build();
//
//        RedisClient redisClient = RedisClient.create(uri);
//
//        StatefulRedisConnection<String, String> connect = redisClient.connect();
//
//        RedisCommands<String, String> redisCommands = connect.sync();
//        SetArgs ex = SetArgs.Builder.nx().ex(5);
//        String set = redisCommands.set("name", "throwable");
//        System.out.println(set);
//
//    }




}
