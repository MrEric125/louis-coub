package com.future;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.collections4.list.PredicatedList;
import org.apache.commons.lang3.StringUtils;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.math.BigDecimal;
import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

import static java.util.stream.Collectors.toList;

/**
 * @author louis
 * <p>
 * Date: 2019/8/22
 * Description:
 */
@Entity
@Table(name = "tb_shop")

@Data
@NoArgsConstructor
public class Shop {

    @Column(name = "shop_name")
    private String shopName;

    @Transient
    private List<Product> productList = new ArrayList<>();


    public List<Product> addProduct(List<Product> products) {
        productList.addAll(products);
        return productList;
    }


    public Shop(String shopName) {
        this.shopName = shopName;
    }

    public BigDecimal getProductPrice(String productName) {
        return productList
                .stream()
                .filter(product -> StringUtils.equals(product.getProductName(), productName))
                .findAny()
                .map(Product::getPrice)
                .orElse(BigDecimal.ZERO);

    }
}
