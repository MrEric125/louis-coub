package com.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.DataType;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.EnumSet;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author louis
 * <p>
 * Date: 2019/11/25
 * Description:
 */
public class RedisUtil {

    private static final Map<String, DataType> codeLookup = new ConcurrentHashMap<>();

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    static {
        for (DataType type : EnumSet.allOf(DataType.class))
            codeLookup.put(type.code(), type);
    }

//    public void get(String key) {
//        DataType type = redisTemplate.type(key);
//        DataType dataType = codeLookup.get(type == null ? null : type.code());
//        if (StringUtils.equals("string", dataType.code())) {
//            String Value = redisTemplate.boundValueOps(key).get();
//        }else if (StringUtils.equals())
//    }

    public static void main(String[] args) {
        codeLookup.forEach((x,y)-> System.out.println(x));

    }


}
