package com;

import com.louis.common.common.WrapMapper;
import com.louis.common.common.Wrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@EnableJpaRepositories
@EnableRedisRepositories
@Slf4j
@SpringBootApplication
@Controller
@CrossOrigin
@RequestMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class BootMybatisApplication {

    public static void main(String[] args) {
        SpringApplication.run(BootMybatisApplication.class, args);
    }

    public String getSomeThing() {
        return "getSomeThing by log";
    }

    @RequestMapping("/login")
    @ResponseBody
    public Wrapper login() {
        String returenData = "this is login test data";
        log.info(returenData);
        return WrapMapper.ok(returenData);
    }

    @RequestMapping("/")
    @ResponseBody
    public Wrapper home() {
        String returnData = "home page";
        log.info(returnData);
        return WrapMapper.ok(returnData);
    }

    @RequestMapping("demo")
    public String index() {
        log.info("index.html>>>>>>>>>>>>>");
        return "index.html";
    }
    @RequestMapping("demo2")
    public String demo2() {
        log.info("index>>>>>>>>>>>>>");
        return "index";
    }




}
