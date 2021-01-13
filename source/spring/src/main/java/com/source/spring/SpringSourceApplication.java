package com.source.spring;

import com.louis.common.common.HttpResult;
import com.source.spring.mvc.MyEvent;
import com.source.spring.mvc.MyListener;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author jun.liu
 * @since 2020/11/14 14:34
 */
@RestController
@SpringBootApplication
public class SpringSourceApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(SpringSourceApplication.class, args);
        run.publishEvent(new MyEvent(new Object()));

    }

    @RequestMapping("/env")
    public HttpResult env(@RequestBody(required = false) String data) {
        return HttpResult.ok(data);
    }
}
