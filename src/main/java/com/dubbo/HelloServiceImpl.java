package com.dubbo;

import org.apache.dubbo.config.annotation.Service;

/**
 * @author louis
 * <p>
 * Date: 2019/8/8
 * Description:
 */
@Service
public class HelloServiceImpl implements HelloService{

    @Override
    public String sayHello(String name) {
        System.out.println("provider recieve sayHello" + name);
        return "hello " + name;
    }

}
