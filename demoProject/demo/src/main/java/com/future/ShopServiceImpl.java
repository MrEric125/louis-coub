package com.future;

import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.NoSuchMessageException;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;
import java.util.concurrent.*;

import static java.util.stream.Collectors.toList;

/**
 * @author jun.liu
 * @date created on 2020/5/4
 * description:
 */
@Service
public class ShopServiceImpl {

    private  List<Shop> shopList = Lists.newCopyOnWriteArrayList();

    private Random random = new Random();


    @Autowired
    private ThreadPoolTaskExecutor executor;

    public List<Shop> getShopList() {
        return shopList;
    }

    public void future() {
        ExecutorService executorService = Executors.newCachedThreadPool();
        Future<String> submit = executorService.submit(() -> "ss");

    }

    /**
     * 通过商店名字找寻商店
     * @param name
     * @return
     */
    public Shop getByShopName(String name) {
        return shopList.stream().filter(item -> StringUtils.equals(item.getShopName(), name))
                .findAny()
                .orElseThrow(() -> new NoSuchMessageException("没有找到对应的商店"));
    }


    /**
     * 创建一个shopList
     * @param shopNames
     * @return
     */
    public  List<Shop> creatShop(List<String> shopNames) {
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
                new Shop("BuyItDDal"));


        List<Shop> list = Optional.ofNullable(shopNames)
                .map(names -> names.stream().map(Shop::new).collect(toList())).orElse(shops);

        shopList.addAll(list);
        return shopList;
    }


    /**
     *  不适用线程池 批量获取不同产品在不同商店的价格
     * @param product
     * @return
     */
    public List<String> findPrice(String product) {
        return shopList.stream()
                .map(shop -> String.format("%s price is %.2f",
                        shop.getShopName(), this.getPrice(product)))
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
        return String.format("%s:%.2f:%s", null, price, code);
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
    private void delay() {
        try {
            Thread.sleep(1000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }


    /**
     * 直接使用流的方式来计算产品价格
     * @param shops
     * @param product
     * @return
     */
    private   List<String>  findPrices1(List<Shop> shops,String product) {
        return shops.stream()
                .map(shop -> {
                    BigDecimal price = shop.getProductList()
                            .stream()
                            .filter(p -> StringUtils.equals(product, p.getProductName()))
                            .map(pro -> pro.getPrice())
                            .findAny()
                            .orElse(BigDecimal.ZERO);
                    return String.format("at %s, %s price is %.2f",
                            shop.getShopName(), product, price);
                })
                .collect(toList());

    }


    /**
     * 通过并行流的方式来计算
     * @param shops
     * @param product
     * @return
     */
    public  List<String> findPrices2(List<Shop> shops,String product) {
        return shops.parallelStream()
                .map(shop -> String.format("%s price is %.2f",
                        shop.getShopName(), shop.getProductPrice(product)))
                .collect(toList());
    }

    /**
     * 通过CompletableFuture 默认的线程池的方式来计算价格
     * @param shops
     * @param product
     * @return
     */
    private   List<String>  findPrices3(List<Shop> shops,String product) {

        return shops.stream()
                .map(shop -> CompletableFuture.supplyAsync(() ->
                        String.format("%s price is %.2f",
                                shop.getShopName(), shop.getProductPrice(product))))
                .map(CompletableFuture::join)
                .collect(toList());
    }


    /**
     *重新新建一个线程池来执行
     * @param shops
     * @param product
     * @param threadNum 线程数量
     * @return
     */
    private   List<String> findPrices4(List<Shop> shops,String product,int threadNum) {
        ExecutorService executor = Executors.newFixedThreadPool(threadNum);
        List<CompletableFuture<String>> collect = shops
                .stream()
                .map(shop -> CompletableFuture.supplyAsync(() ->
                        String.format("%s price is %.2f",
                                shop.getShopName(), shop.getProductPrice(product)), executor)
                )
//                .map(future->future.thenApply())
//                .map(future->future.thenCompose())
                .collect(toList());
        executor.shutdown();
        return collect.stream().map(CompletableFuture::join).collect(toList());
    }

    /**
     *ThreadPoolTaskExecutor 来作为线程池执行任务
     * @param shops
     * @param product

     * @return
     */
    private   List<String> findPrices5(List<Shop> shops,String product) {
        List<CompletableFuture<String>> collect = shops
                .stream()
                .map(shop -> CompletableFuture.supplyAsync(() ->
                        String.format("%s price is %.2f",
                                shop.getShopName(), shop.getProductPrice(product)), executor)
                )
                .collect(toList());
        return collect.stream().map(CompletableFuture::join).collect(toList());
    }


    /**
     * 运行策略，具体使用哪种计算方式来计算
     */
    public   List<String> runStratage( String product, int stratageCode,int threadNum) {
        List<String> object;
        switch (stratageCode) {
            case 1:
                object= findPrices1(shopList, product);
                break;
            case 2:
                object= findPrices2(shopList, product);
                break;
            case 3:
                object= findPrices3(shopList, product);
                break;
            case 4:
                object= findPrices4(shopList, product,threadNum);
                break;
            case 5:
                object = findPrices5(shopList, product);
                break;

            default:
                object=findPrices1(shopList, product);
                break;
        }
        return object;
    }

    public static void main(String[] args) {
        CompletableFuture<Long> future = CompletableFuture.supplyAsync(() -> {
            long result = new Random().nextInt(100);
            System.out.println("result1="+result);
            return result;
        }).thenApply(t -> {
            long result = t*5;
            System.out.println("result2="+result);
            return result;
        });

        long result = future.join();
        System.out.println(result);

    }

}
