package com.louis;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author John·Louis
 * @date created on 2020/2/23
 * description:
 */
//@MapperScan(basePackages= {"com.louis"},sqlSessionFactoryRef="sqlSessionFactory")
@SpringBootApplication
public class MinaShop {
    public static void main(String[] args) {
        SpringApplication.run(MinaShop.class, args);
    }
}
