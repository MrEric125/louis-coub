package com.louis.longagocode.future;

import com.future.Discount;

import java.math.BigDecimal;

/**
 * @author louis
 * <p>
 * Date: 2019/8/23
 * Description:
 */
public class Quote {

    private final String shopName;
    private final BigDecimal price;
    private final Discount.Code discountCode;
    public Quote(String shopName, BigDecimal price, Discount.Code code) {
        this.shopName = shopName;
        this.price = price;
        this.discountCode = code;
    }
    public static Quote parse(String s) {
        String[] split = s.split(":");
        String shopName = split[0];
        BigDecimal price = BigDecimal.valueOf(Double.parseDouble(split[1]));
        Discount.Code discountCode = Discount.Code.valueOf(split[2]);
        return new Quote(shopName, price, discountCode);
    }
    public String getShopName() { return shopName; }
    public BigDecimal getPrice() { return price; }
    public Discount.Code getDiscountCode() { return discountCode; }
}
