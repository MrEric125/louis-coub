package com.louis;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.HashMap;
import java.util.Map;

/**
 * @author louis
 * <p>
 * Date: 2019/9/16
 * Description:
 */
@EnableDubbo
@SpringBootApplication
public class ConsumerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ConsumerApplication.class, args);
        Map<String, String> hashMpa = new HashMap<>();
        hashMpa.put("zhangsan", "33");

    }
}
