package com.onetomany;

import lombok.Data;

import java.util.Date;

@Data
public class Items {
    /** 商品表主键Id */
    private Integer id;
    /** 商品名称 */
    private String itemsName;
    /** 商品定价 */
    private float price;
    /** 商品描述 */
    private String detail;
    /** 商品图片 */
    private String picture;
    /** 生产日期 */
    private Date createTime;
// getter and setter ......
}