package com.louis.longagocode.future;

import com.google.common.collect.ImmutableMap;
import com.louis.common.common.HttpResult;
import org.assertj.core.util.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author jun.liu
 * @date created on 2020/5/4
 * description:
 */
@RequestMapping("future")
@RestController
public class CompletableFutureController  {
    @Autowired
    private ShopServiceImpl shopService;


    @GetMapping("/shopList")
    public HttpResult getShopList() {
        List<Shop> shopList = shopService.getShopList();
        return HttpResult.ok(shopList);
    }

    @PostMapping("/createShop")
    public HttpResult createShop(String shopName) {
        List<Shop> shops = shopService.creatShop(shopName==null?null:Lists.newArrayList(shopName));
        int size = shops.size();
        Map map = ImmutableMap.of("item", shops, "size", size);
        return HttpResult.ok(map);
    }

    @PostMapping("/createProduct")
    public HttpResult createProduct(String productNam, BigDecimal productPrice) {
        List<Shop> shopList = shopService.getShopList();
        shopList.forEach(item->{
            Product product = new Product();
            product.setProductName(productNam);
            product.setPrice(productPrice);
            item.addProduct(Lists.newArrayList(product));

        });
        return HttpResult.ok(shopList);
    }



    @GetMapping("/streamGroup")
    public HttpResult streamGroup() {
        List<Shop> shops = shopService.getShopList();
        Map<String, List<Shop>> collect = shops
                .stream().collect(Collectors.groupingBy(Shop::getShopName));
        return HttpResult.ok(collect);
    }

    @GetMapping("/findPrice")
    public HttpResult findPrice(String product,int stratageCode,Integer threadNum) {
        List<String> list = shopService.runStratage(product, stratageCode, threadNum);
       return HttpResult.ok(list);
    }
}
