package com.louis.mybatis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * @author louis
 * <p>
 * Date: 2019/9/11
 * Description:
 */
@MapperScan("com.louis.mybatis.tkmybatis.mapper")
@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
