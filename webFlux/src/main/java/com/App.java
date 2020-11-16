package com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.web.reactive.config.EnableWebFlux;

/**
 * @author louis
 * <p>
 * Date: 2019/9/19
 * Description:
 * todo 如何将当前程序标示为 WebFlux的， 貌似{@link EnableWebFlux} 不起作用
 */
@EnableWebFlux
@SpringBootApplication
public class App {
    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }
}
