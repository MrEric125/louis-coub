package com.louis.dubbosourceconsumer;

import com.louis.common.common.HttpResult;
import com.louis.dubbo.DefaultDubboService;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController

public class DubboSourceConsumerApplication {

    @Reference
    DefaultDubboService dubboService;

    public static void main(String[] args) {
        SpringApplication.run(DubboSourceConsumerApplication.class, args);
    }

    @RequestMapping("sayHello")
    public HttpResult httpResult(String name) {

        return HttpResult.ok(dubboService.sayHello(name));
    }

}
