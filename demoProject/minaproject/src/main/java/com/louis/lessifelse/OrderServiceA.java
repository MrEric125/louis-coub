package com.louis.lessifelse;

import org.springframework.stereotype.Service;

@Service
public class OrderServiceA {

    public void orderService(Order order) {
        if(order.getSource().equals("pc")){
            // 处理pc端订单的逻辑
        }else if(order.getSource().equals("mobile")){
            // 处理移动端订单的逻辑
        }else {
            // 其他逻辑
        }
    }
}