package com.louis.longagocode.future;

import com.future.Shop;

import java.util.List;


/**
 * @author louis
 * <p>
 * Date: 2019/8/23
 * Description:
 * 根据不同会员大不同折扣
 */
public class Discount {

    public enum Code{
        NONE(0), SILVER(5), GOLD(10), PLATINUM(15), DIAMOND(20);
        private final int percentage;

        Code(int percentage) {
            this.percentage = percentage;
        }
    }

    public List<String> findPrices(List<Shop> shops, String product) {
//        return shops.stream()
//                .map(shop -> shop.getProductPrice(product))
//                .map(Quote::parse)
//                .map(Discount::applyDiscount)
//                .collect(Collectors.toList());
        return null;

    }



    public static String applyDiscount(Quote quote) {
        return quote.getShopName() + " price is " +
                Discount.apply(quote.getPrice().doubleValue(),
                        quote.getDiscountCode());
    }
    private static double apply(double price, Code code) {
//        delay();
        return format(price * (100 - code.percentage) / 100);
    }

    private static double format(double price) {
        return Double.parseDouble(String.format("%.2f", price));
    }


}
