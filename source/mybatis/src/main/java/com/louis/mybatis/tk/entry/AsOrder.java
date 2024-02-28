package com.louis.mybatis.tk.entry;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 售后订单表
 * </p>
 *
 * @author xiongkunwei
 * @since 2021-08-06
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("as_order")
public class AsOrder extends Model<AsOrder> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 工单ID
     */
    private Long orderId;

    /**
     * 客户id
     */
    private Long cusId;

    /**
     * 客户名称
     */
    private String cusName;

    /**
     * 客户联系方式
     */
    private String cusPhone;

    /**
     * 下单时间
     */
    private Date orderTime;

    /**
     * 妥投时间
     */
    private Date completeTime;

    /**
     * 配送方式
     */
    private Integer shipmentType;

    /**
     * 配送类型
     */
    private Integer paymentType;

    /**
     * SKU数量
     */
    private Integer skuNum;

    /**
     * 商品总数量
     */
    private Integer wareNum;

    /**
     * 商家编号
     */
    private Long venderId;

    /**
     * 结算类型
     */
    private String tradeType;

    /**
     * 用户地址
     */
    private String address;


    @Override
    public Serializable pkVal() {
        return this.id;
    }

}
