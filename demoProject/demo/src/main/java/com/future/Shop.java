package com.future;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.collections4.list.PredicatedList;
import org.apache.commons.lang3.StringUtils;

import javax.persistence.*;
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
@AllArgsConstructor
public class Shop {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "shop_name")
    private String shopName;

    @Transient
    private List<Product> productList = new ArrayList<>();


    @Transient
    public List<Product> addProduct(List<Product> products) {
        productList.addAll(products);
        return productList;
    }


    public Shop(String shopName) {
        this.shopName = shopName;
    }

    @Transient
    public BigDecimal getProductPrice(String productName) {
        return productList
                .stream()
                .filter(product -> StringUtils.equals(product.getProductName(), productName))
                .findAny()
                .map(Product::getPrice)
                .orElse(BigDecimal.ZERO);

    }
}
