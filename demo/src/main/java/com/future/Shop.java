package com.future;

import lombok.NoArgsConstructor;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

import static java.util.stream.Collectors.toList;

/**
 * @author louis
 * <p>
 * Date: 2019/8/22
 * Description:
 */
@NoArgsConstructor
public class Shop {
    private String shopName;
    Random random = new Random();
    private Integer price;

    public Shop(String shopName) {
        this.shopName = shopName;
    }


    public static List<Shop> creatShop(List<String> shopNames) {
        return Optional.ofNullable(shopNames)
                .map(names -> names.stream().map(Shop::new).collect(toList())).orElseGet(()->{
            List<Shop> shops = Arrays.asList(new Shop("BestPrice"),
                    new Shop("LetsSaveBig"),
                    new Shop("MyFavoriteShop"),
                    new Shop("LLEages"),
                    new Shop("Myabatss"),
                    new Shop("BuyItAlssa"),
                    new Shop("BuyItDDal"),
                    new Shop("BuyItDDal"),
                    new Shop("LLEages"),
                    new Shop("Myabatss"),
                    new Shop("BuyItAlssa"),
                    new Shop("BuyItDDal"),
                    new Shop("BuyItDDal")

            );
            return shops;
        });
    }


    /**
     * 批量获取不同产品在不同商店的价格
     * @param shops
     * @param product
     * @return
     */
    public List<String> findPrice(List<Shop> shops,String product) {
        return shops.stream()
                .map(shop -> String.format("%s price is %.2f",
                        shop.getShopName(), shop.getPrice(product)))
                .collect(toList());

    }

    /**
     * 通过异步的方式获取商品价格
     * @param product
     * @return
     */
    public Future<Double> getPriceAsync(String product){
        CompletableFuture<Double> futurePrice = new CompletableFuture<>();
        new Thread(() -> {
            try {
                double price = calculatePrice(product);
                futurePrice.complete(price);

            } catch (Exception e) {
                futurePrice.completeExceptionally(e);
            }
        }).start();
        return futurePrice;
    }

    /**
     * 通过产品名称计算产品价格
     * @param product
     * @return
     */
    public Future<Double> getPriceAsync2(String product){
        return CompletableFuture.supplyAsync(() -> calculatePrice(product));
    }





    public String getPrice(String product, String type) {
        double price = calculatePrice(product);
        Discount.Code code = Discount.Code.values()[
                random.nextInt(Discount.Code.values().length)];
        return String.format("%s:%.2f:%s", shopName, price, code);
    }

    /**
     * 获取计算到的价格
     * @param produce
     * @return
     */
    public double getPrice(String produce) {
        return calculatePrice(produce);

    }

    private double calculatePrice(String product) {
        delay();
        return random.nextDouble() * product.charAt(0) + product.charAt(1);

    }

    /**
     * 延时操作
     */
    static void delay() {
        try {
            Thread.sleep(1000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    @Override
    public String toString() {
        return "Shop{" +
                "shopName='" + shopName + '\'' +
                ", random=" + random +
                '}';
    }


    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }
}
