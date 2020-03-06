package com;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author louis
 * <p>
 * Date: 2019/9/16
 * Description:
 */
@SpringBootApplication
@RestController
@EnableSwagger2
@EnableDubbo
public class ProducerApp {
    public static void main(String[] args) {
        SpringApplication.run(ProducerApp.class, args);
    }

    @RequestMapping("/home")
    public String home() {
        return "home";
    }
}
