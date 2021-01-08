package com;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author louis
 * <p>
 * Date: 2019/9/11
 * Description:
 */
//@MapperScan("com.louis.mybatis.tkmybatis.mapper")
@MapperScan({"com.louis.mybatis.dynamic.mapper"})
@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
        System.out.println("aa");
    }
}
