package com.onetomany;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class Orders {
    /** 主键订单Id */
    private Integer id;
    /** 下单用户id */
    private Integer userid;
    /** 订单号 */
    private String number;
    /** 创建订单时间 */
    private Date createTime;
    /** 备注 */
    private String note;
    // 用户信息
    private User user;
    // 订单明细
    private List<OrderDetail> orderdetails;
       //  getter and setter ......
}