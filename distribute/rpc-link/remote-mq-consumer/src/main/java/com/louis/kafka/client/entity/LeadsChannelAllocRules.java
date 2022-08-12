package com.louis.kafka.client.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;
import sun.rmi.runtime.Log;

import java.io.Serializable;
import java.util.Date;

/**
 * @author louis
 * @date 2022/8/12
 */
@Data
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class LeadsChannelAllocRules implements Serializable {


    private static final long serialVersionUID = -8348176928069724894L;

    private Long id;

    /**
     * 商户id,全局配置biz_id 为0
     */
    @JsonProperty("biz_id")
    private Long bizId;

    @JsonProperty("source_type")
    private Integer sourceType;

    /**
     * 默认处理方式： 1： 系统自动分配，
     * 2： 分配到微信好友成员，无好友，系统分配，
     * 3.分配给成单人,无成单人系统分配，
     * 4：分配给分享人，
     * 5： 分配给指定跟进人
     */
    @JsonProperty("handle_type")
    private Integer handleType;

    /**
     * 分配的目标库类型： 1： 临时库； 2： 私有库
     */
    @JsonProperty("alloc_target_customer_type")
    private Integer allocTargetCustomerType;

    /**
     * 分配成单人金额足够限制： 2：金额足够分配到成交库
     */
    @JsonProperty("full_amount_customer_type")
    private Integer fullAmountCustomerType;

    /**
     * 库容限制 1: 超出库容进行系统自动分配，2：允许库容溢出
     */
    @JsonProperty("lib_capacity_limited")
    private Integer libCapacityLimited;

    /**
     * 启用状态
     */
    private Integer status;

    /**
     * 创建人id
     */
    @JsonProperty("create_by")
    private Long createBy;

    /**
     * 创建时间
     */
    @JsonProperty("create_time")
    private Date createTime;

    /**
     * 更新人id
     */
    @JsonProperty("update_by")
    private Long updateBy;

    /**
     * 更新时间
     */
    @JsonProperty("update_time")
    private Date updateTime;

    @JsonProperty("column_13")
    private Integer column13;
}
