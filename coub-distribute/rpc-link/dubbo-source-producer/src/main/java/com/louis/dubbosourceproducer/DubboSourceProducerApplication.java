package com.louis.dubbosourceproducer;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@SpringBootApplication
@RestController
@RequestMapping("/test")
@EnableDubbo
public class DubboSourceProducerApplication {


    public static void main(String[] args) {
        SpringApplication.run(DubboSourceProducerApplication.class, args);
    }


}
