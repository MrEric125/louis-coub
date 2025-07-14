package com.louis.mybatis;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author louis
 * <p>
 * Date: 2019/9/11
 * Description:
 */
@EnableTransactionManagement
@MapperScan({"com.louis.mybatis.tk.mapper"})
@SpringBootApplication
public class MyBatisApplication {

     public static void main(String[] args) {
        SpringApplication.run(MyBatisApplication.class, args);
    }
}
