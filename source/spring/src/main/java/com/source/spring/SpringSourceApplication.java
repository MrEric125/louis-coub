package com.source.spring;

import com.alibaba.fastjson.JSON;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author jun.liu
 * @since 2020/11/14 14:34
 *  {@link SpringBootApplication} 起作用的核心配置是@EnableAutoConfiguration
 *
 * {@link org.springframework.context.annotation.ComponentScan} 只是spring mvc xml功能迁移而已，扫描当前包下的所有带配置类
 *
 */

@EnableTransactionManagement
@MapperScan(basePackages= {"com.source"},sqlSessionFactoryRef="sqlSessionFactory")
@RestController
@SpringBootApplication
public class SpringSourceApplication implements CommandLineRunner {


    public static void main(String[] args) {
        SpringApplication sa = new SpringApplication(SpringSourceApplication.class);
        sa.run();
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println(JSON.toJSONString(args));
    }


}
