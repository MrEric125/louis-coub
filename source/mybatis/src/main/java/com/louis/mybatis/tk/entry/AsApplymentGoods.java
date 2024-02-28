package com.louis.mybatis.tk.entry;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 售后退货受理单-商品详情
 * </p>
 *
 * @author xiongkunwei
 * @since 2021-07-29
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("as_applyment_goods")
public class AsApplymentGoods extends Model<AsApplymentGoods> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 售后单号
     */
    private Long applyId;

    /**
     * 订单号
     */
    private Long orderId;

    /**
     * 商品sku
     */
    private Long skuId;

    /**
     * 拣货码
     */
    private String matnr;

    /**
     * 商品名称
     */
    private String wareName;

    /**
     * 购买数量
     */
    private Integer wareNum;

    /**
     * 商品类型(1:普通,2:赠品,3:附件)
     */
    private Integer wareType;

    /**
     * SKU类型(1:标品,2:散卖)
     */
    private Integer skuType;

    /**
     * 商品单价
     */
    private Long warePrice;

    /**
     * 退货数量
     */
    private Integer returnedNum;

    /**
     * 退款金额
     */
    private Long returnedAmount;

    /**
     * 售后商品原始价格
     */
    private Long refundWareOriginPrice;

    /**
     * 退还积分
     */
    private Long returnedCredits;

    /**
     * 源商品ID
     */
    private Long sourceWareId;

    /**
     * 对应订单的商品行主键id
     */
    private Long orderWareId;

    /**
     * 真实退款金额
     */
    private Long actualReturnedAmount;

    /**
     * 订单系统ware主键
     */
    private Long wareDbId;

    /**
     * 扫码
     */
    private String scanPlu;

    /**
     * 国条码
     */
    private String itemNum;

    /**
     * Vpos主键Id
     */
    private Long pickLogId;

    /**
     * 退货重量
     */
    private Double weight;

    /**
     * 税率
     */
    private String taxRate;

    /**
     * 条码行金额
     */
    private Long wareCodeAmount;

    /**
     * 条码行单价
     */
    private Long wareCodePrice;

    /**
     * 订单来源
     */
    private Integer orderOrigin;

    /**
     * 商品行实退金额
     */
    private Long realRefundAmount;

    /**
     * 基本单位
     */
    private String unit;

    /**
     * 条码行申请售后重量
     */
    private Double refundWeight;

    /**
     * 支付方式信息及对应的金额
     */
    private String payMethodInfos;

    /**
     * 手动改价时，由改的价格计算出来的金额分摊信息
     */
    private String manualPayMethodInfos;

    /**
     * 商家编号
     */
    private Long venderId;

    /**
     * 结算类型
     */
    private String tradeType;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;



    /**
     * 实际退款数量
     */
    private Integer realReturnedNum;

    /**
     * 实际退款重量
     */
    private Double realRefundWeight;

    @Override
    public Serializable pkVal() {
        return this.id;
    }


}
