package com.louis;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.ExitCodeGenerator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @author John·Louis
 * @date created on 2020/2/23
 * description:
 */
@MapperScan(basePackages= {"com.louis"},sqlSessionFactoryRef="sqlSessionFactory")
@SpringBootApplication
public class MinaProject implements ExitCodeGenerator {
    public static void main(String[] args) {
        SpringApplication.run(MinaProject.class, args);

//        System.exit(SpringApplication.exit(SpringApplication.run(MinaProject.class, args)));
    }

    @Override
    public int getExitCode() {
        return 01111;
    }
}

@Component
@Order(value = 1212)
class MyBean implements CommandLineRunner {

    @Override
    public void run(String... args) throws Exception {
        System.out.println(args);

    }
}
