package com.source.spring;

import com.alibaba.fastjson.JSON;
import com.louis.common.common.HttpResult;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tk.mybatis.spring.annotation.MapperScan;

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



    @RequestMapping("/env")
    public HttpResult env(@RequestBody() String data)  {
        return HttpResult.ok(data);
    }
    @RequestMapping("/add")
    public HttpResult add(String data,String data2) {
        return HttpResult.ok(data);
    }


    @RequestMapping("/ ")
    public HttpResult rootPage() {
        return HttpResult.ok("zhangsan");
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println(JSON.toJSONString(args));
    }


}
