package com.onetomany;

import lombok.Data;

@Data
public class OrderDetail {
    /** 主鍵，訂單明细表Id */
    private Integer id;
    /** 訂單Id */
    private Integer ordersId;
    /** 商品id */
    private Integer itemsId;
    /** 商品购买数量 */
    private Integer itemsNum;
    // 明细对应的商品信息
    private Items items;
        //  getter and setter ......
}