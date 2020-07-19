package provider.impl;

import provider.api.HelloService;

/**
 * @author jun.liu
 * @date created on 2020/7/19
 * description:
 */
public class HelloServiceImpl implements HelloService {


    @Override
    public String hello(String param) {
        return "hello " + param;
    }
}
