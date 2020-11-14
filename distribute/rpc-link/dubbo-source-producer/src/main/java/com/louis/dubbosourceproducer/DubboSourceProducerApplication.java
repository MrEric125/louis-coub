package com.louis.dubbosourceproducer;

import com.google.common.collect.Maps;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
@RestController
@RequestMapping("/test")
public class DubboSourceProducerApplication {

    public static void main(String[] args) {
        SpringApplication.run(DubboSourceProducerApplication.class, args);
    }

    @RequestMapping("/test")
    public Map<String,Object> testResult() {
        HashMap<String, Object> ha = Maps.newHashMap();
        ha.put("zhangsan", "zhansss");
        return ha;

    }

}
