package com.louis.dubbosourceconsumer;

import com.louis.common.common.HttpResult;
import com.louis.dubbo.DefaultDubboService;
import com.louis.dubbo.DubboService2;
import org.apache.dubbo.config.annotation.Reference;
import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;


/**
 * dubbo source article to
 * @see {}https://www.cnblogs.com/cyfonly/p/9127712.html
 */
@SpringBootApplication
@RestController
@EnableDubbo
public class DubboSourceConsumerApplication {

    @Reference(check = false)
    DefaultDubboService dubboService;

    @Reference(check = false)
    DubboService2 dubboService2;

    public static void main(String[] args) {
        SpringApplication.run(DubboSourceConsumerApplication.class, args);
    }

    @RequestMapping("sayHello")
    public HttpResult httpResult(String name) {
        Map<String, Object> stringObjectMap = dubboService.sayHello(name);
        String s = dubboService2.dubboService2();
        stringObjectMap.put("dubboService2", s);
        return HttpResult.ok(stringObjectMap);
    }

}
