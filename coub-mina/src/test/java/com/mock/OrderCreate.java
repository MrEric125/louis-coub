package com.mock;

import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class OrderCreate {

    @Resource
    private OrderCreate orderCreate;

    public double create() {
        // 模拟订单创建逻辑
        System.out.println("Creating order...");

        double apply = Operation.DIVIDE.apply(12, 23);


        System.out.println("Operation result: " + apply);

        // 调用其他服务或组件
//        orderCreate.processOrder();
        return OperationLambda.DIVIDE.apply(12, 23);
    }


}

