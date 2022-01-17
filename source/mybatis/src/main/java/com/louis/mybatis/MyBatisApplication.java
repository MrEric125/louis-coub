package com.louis.mybatis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * @author louis
 * <p>
 * Date: 2019/9/11
 * Description:
 */
@EnableTransactionManagement
@MapperScan("com.louis.mybatis.tk")
@SpringBootApplication
public class MyBatisApplication {

     public static void main(String[] args) {
        SpringApplication.run(MyBatisApplication.class, args);
    }
}
