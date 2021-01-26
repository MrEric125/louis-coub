package com.onetomany;

import java.util.List;



public interface OrdersCustomMapper {
    /** 查询订单，关联查询用户信息 */
    public List<OrdersCustom> findOrdersUser();

    public List<Orders>findOrdersUserResultMap();

    public List<Orders> findOrdersAndOrderDetailResultMap();
}