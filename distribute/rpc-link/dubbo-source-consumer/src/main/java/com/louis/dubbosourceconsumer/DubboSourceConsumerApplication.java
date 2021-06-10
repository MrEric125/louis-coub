package com.louis.dubbosourceconsumer;

import com.louis.common.common.HttpResult;
import com.louis.dubbo.DefaultDubboService;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@SpringBootApplication
public class DubboSourceConsumerApplication {

    public static void main(String[] args) {
        SpringApplication.run(DubboSourceConsumerApplication.class, args);
    }

    @Reference
    public DefaultDubboService defaultDubboService;


    @RequestMapping("/dubbo")
    public HttpResult dubbo(String name) {
        return HttpResult.ok(defaultDubboService.sayHello(name));
    }

}
