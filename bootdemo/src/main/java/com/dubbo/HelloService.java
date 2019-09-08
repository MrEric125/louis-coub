package com.dubbo;

/**
 * @author louis
 * <p>
 * Date: 2019/8/8
 * Description:
 */

public interface HelloService {

    String sayHello(String name);

    default String sayGoodbye(String name) {
        return "goodBye" + name;
    }
}
