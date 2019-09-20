package com;

import com.louis.bootmybatis.common.WrapMapper;
import com.louis.bootmybatis.common.Wrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * #2019-07-10 17:45:44.852|INFO |qtp6750210-57| com.sf.novatar.deploy.interceptor.ModuleInterceptor.intercept(ModuleInterceptor.java:70)|Response /frame.pvt in 780 ms.
 * 2019-07-10 17:43:52.404  INFO 12572 --- [      Thread-39] o.s.s.concurrent.ThreadPoolTaskExecutor  : Shutting down ExecutorService 'applicationTaskExecutor'
 */
@Slf4j
@SpringBootApplication
@Controller
//@EnableCircuitBreaker
@EnableHystrix
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
        Integer integer = new Integer(4);
        String returnData = "home page";
        log.info(returnData);
        return WrapMapper.ok(returnData);
    }



}
