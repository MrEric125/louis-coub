package com.future;

import com.alibaba.fastjson.JSONObject;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author louis
 * <p>
 * Date: 2019/12/11
 * Description:
 */
public class StreamTest {

    public static void main(String[] args) {
        List<Shop> shops = Shop.creatShop(null);
        Map<String, Map<Double, List<Shop>>> collect = shops.stream().collect(Collectors.groupingBy(Shop::getShopName, Collectors.groupingBy(x -> x.getPrice(x.getShopName()))));
        String string = JSONObject.toJSONString(collect, true);
        System.out.println(string);
    }
}
