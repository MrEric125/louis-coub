package com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.bind.annotation.CrossOrigin;

@EnableTransactionManagement
@EnableJpaRepositories
 @SpringBootApplication
 @CrossOrigin
public class BootMybatisApplication {

    public static void main(String[] args) {
         SpringApplication.run(BootMybatisApplication.class, args);
    }
    



}
