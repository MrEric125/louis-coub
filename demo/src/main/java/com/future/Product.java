package com.future;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;

/**
 * @author jun.liu
 * @date created on 2020/5/4
 * description:
 *  不同商店销售的产品
 */
@Entity
@Table(name = "tb_product")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {

    @Column(name = "product_name")
    private String productName;

    @Column(name = "price")
    private BigDecimal price;
}

