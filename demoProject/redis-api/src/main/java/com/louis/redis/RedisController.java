//package com.louis.redis;
//
//import com.google.common.collect.ImmutableMap;
//import com.google.gson.Gson;
//import com.louis.common.common.WrapMapper;
//import com.louis.common.common.Wrapper;
//import com.sf.framework.cacheproxy.redis.RedisCache;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.List;
//import java.util.Map;
//import java.util.Set;
//import java.util.stream.Collectors;
//
///**
// * @author louis
// * <p>
// * Date: 2020/2/27
// * Description:
// */
//@Slf4j
//@RestController
//@RequestMapping("redis")
//public class RedisController {
//
//    @Autowired
//    private RedisCache redisCache;
//
//    @Autowired
//    private WmoRedisCache wmoRedisCache;
//
//    private transient Gson gson=new Gson();
//
//
//
//
//    @RequestMapping("/setString")
//    public Wrapper setString(@RequestParam("key") String key,
//                             @RequestParam("value") String value) {
//
//        redisCache.setString(key, value);
//        String string = redisCache.getString(key);
//        return WrapMapper.ok(string);
//    }
//
//    @RequestMapping("/getString")
//    public Wrapper getString(@RequestParam("key") String key){
//        String value = redisCache.getString(key);
//        return WrapMapper.ok(value);
//    }
//
//    @RequestMapping("/keys")
//    public Wrapper keys(@RequestParam("key") String patten) {
//        Set<String> keys = wmoRedisCache.keys(patten);
//
//        List<Object> keyValues = keys.stream().map(key -> {
//            Object obj = null;
//            try {
//                obj = wmoRedisCache.getIgnoreType(key);
//            } catch (Exception e) {
//                String type = redisCache.type(key);
//                log.error("error: key:{};keyType: {}; errorMessage:{}", key, type,e.getMessage());
//            }
//            KeyValue keyValue = new KeyValue();
//            keyValue.setKey(key);
//            keyValue.setValue(obj != null ? gson.toJson(obj) : null);
//            return keyValue;
//        }).filter(keyValue -> keyValue.getValue() != null).collect(Collectors.toList());
//        Map map = ImmutableMap.of("keyValue", keyValues, "totalSize", keys.size(), "realSize", keyValues.size());
//
//        return WrapMapper.ok(map);
//    }
//
//    @RequestMapping("/type")
//    public Wrapper setObj(@RequestParam("key") String pattern){
//        Set<String> keys = wmoRedisCache.keys(pattern);
//        Set<String> types = keys.stream().map(k ->{
//            String type = redisCache.type(k);
////            if (type.equals("none")) {
////                log.info("type ==none; key: {} ", k);
////                Object o = redisCache.get(k);
////                log.info("value of none: {}", gson.toJson(o));
////            }
//
//
//            if (type.equals("hash")) {
//
//                log.info("type ==hash; key: {} ", k);
//            }
////
////            if (type.equals("set")) {
////
////                log.info("type ==set; key: {} ", k);
////            }
////            if (type.equals("list")) {
////
////                log.info("type ==set; key: {} ", k);
////            }
//
//            return type;
//            }).collect(Collectors.toSet());
//
//
//        return WrapMapper.ok(types);
//
//    }
//
//
//    @RequestMapping("/set")
//    public Wrapper setObj(@RequestParam("key") String key, @RequestBody Request request) {
//        redisCache.set(key, request);
//        Request o = redisCache.get(key);
//        return WrapMapper.ok(o);
//    }
//
//
//    @RequestMapping("/get")
//    public Wrapper getObj(@RequestParam("key") String key) {
//        Request o = redisCache.get(key);
//
//        return WrapMapper.ok(o);
//    }
//
//    @RequestMapping("/remove")
//    public Wrapper remove(@RequestParam("key") String key) {
//        redisCache.remove(key);
//
//        return WrapMapper.ok("success");
//    }
//
//
//    @RequestMapping("/setMap")
//    public Wrapper setMap (@RequestParam("key") String key, @RequestBody Request request) {
//        redisCache.hset(key, request.getId(), request.getName());
//        return WrapMapper.ok("success");
//    }
//
//    @RequestMapping("/hGetAll")
//    public Wrapper hGetAll (@RequestParam("key") String key) {
//        Map<String, String> map = redisCache.hgetAll(key);
//        return WrapMapper.ok(map);
//    }
//
//    @RequestMapping("/hdel")
//    public Wrapper hdel (@RequestParam("key") String key,@RequestParam("field") String field) {
//        redisCache.hdel(key, field);
//        return WrapMapper.ok("success");
//    }
//
//
//    @RequestMapping("/setnx")
//    public Wrapper setnx (@RequestParam("key") String key, @RequestBody Request request) {
//        redisCache.setnx(key, request);
//        return WrapMapper.ok("success");
//    }
//    @RequestMapping("/pop")
//    public Wrapper pop(@RequestParam("key") String key) {
//        String popKey=redisCache.pop(key);
//        return WrapMapper.ok(popKey);
//    }
//    @RequestMapping("/push")
//    public Wrapper push(@RequestParam("key") String key) {
//        redisCache.push(key, key);
//        return WrapMapper.ok("success");
//    }
//
//    @RequestMapping("/getCounter")
//    public Wrapper getCounter(@RequestParam("key") String key) {
//        long counter = redisCache.getCounter(key);
//        return WrapMapper.ok("counter:" + counter);
//    }
//
//
//    @RequestMapping("/incCounter")
//    public Wrapper incCounter(@RequestParam("key") String key) {
//        long counter = redisCache.incCounter(key);
//        return WrapMapper.ok("counter:" + counter);
//    }
//
//    @RequestMapping("/decCounter")
//    public Wrapper decCounter(@RequestParam("key") String key) {
//        long counter = redisCache.decCounter(key);
//        return WrapMapper.ok("counter:" + counter);
//    }
//
//
//
//
//
//}
