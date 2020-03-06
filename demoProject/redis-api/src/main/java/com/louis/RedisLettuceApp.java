package com.louis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author louis
 * <p>
 * Date: 2020/2/27
 * Description:
 * // https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-starter-web
 * compile group: 'org.springframework.boot', name: 'spring-boot-starter-web', version: '2.2.5.RELEASE'
 *
 *  <dependency>
 *             <groupId>com.sf.framework</groupId>
 *             <artifactId>sf-framework-cacheproxy</artifactId>
 *             <version>1.2</version>
 *         </dependency>
 *
 *
 * compile group: 'com.sf.framework', name: 'sf-framework-cacheproxy', version: '1.2'
 *
 */
@SpringBootApplication
public class RedisLettuceApp {

    public static void main(String[] args) {
        SpringApplication.run(RedisLettuceApp.class, args);
    }
}
