package com.source.spring;

import com.source.spring.mvc.MyEvent;
import com.source.spring.mvc.MyListener;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * @author jun.liu
 * @since 2020/11/14 14:34
 */
@SpringBootApplication
public class SpringSourceApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(SpringSourceApplication.class, args);
        run.publishEvent(new MyEvent(new Object()));

    }
}
