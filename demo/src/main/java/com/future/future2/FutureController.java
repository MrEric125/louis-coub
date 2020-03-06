package com.future.future2;

import com.google.common.collect.ImmutableMap;
import com.louis.common.common.WrapMapper;
import com.louis.common.common.Wrapper;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;

import static java.util.stream.Collectors.toList;

/**
 * @author louis
 * <p>
 * Date: 2019/8/28
 * Description:
 */
@RestController
public class FutureController {

    private List<Shop> list = new ArrayList<>();

    @Autowired
    ThreadPoolTaskExecutor executor;

    @RequestMapping("toList/{num}")
    public Wrapper addToList(@PathVariable int num) {
        if (!CollectionUtils.isEmpty(list)) {
            list.clear();
        }

        long startTime = System.currentTimeMillis();
        for (int i = 0; i < num; i++) {
            String random = RandomStringUtils.randomAlphabetic(5);
            list.add(new Shop(random));
        }
        long endTime = System.currentTimeMillis();
        Map<String, Object> map = ImmutableMap.of("list", list, "time", endTime - startTime);
        return WrapMapper.ok(map);
    }
    @RequestMapping("getList")
    public Wrapper getList() {
        return WrapMapper.ok(list);
    }

    @RequestMapping("price/{product}/{threadNum}")
    public Wrapper getPrice(@PathVariable String product ,@PathVariable int threadNum) {
        long start = System.currentTimeMillis();
        List<String> prices = findPrices(list, product,threadNum);
        long end = System.currentTimeMillis();
        Map<String,Object> map = ImmutableMap.of( "time", end - start);
        return WrapMapper.ok(map);

    }



    @RequestMapping("/threadNum")
    public Wrapper getThread() {
        ThreadGroup threadGroup = Thread.currentThread().getThreadGroup();
        int groupCount = threadGroup.activeGroupCount();
        int count = threadGroup.activeCount();
        int parentCount = threadGroup.getParent().activeCount();


        Map<String, Object> map = ImmutableMap
                .of("groupCount", groupCount, "threadCount", count,"parentGroupCount",parentCount);

        return WrapMapper.ok(map);
    }

    private  List<String> findPrices(List<Shop> shops,String product,Integer threadNum) {
//        ExecutorService executor = Executors.newFixedThreadPool(threadNum == null ? 8 : threadNum);
        if (executor.getMaxPoolSize() < threadNum) {
            executor.setMaxPoolSize(threadNum);
        }
        List<String > collect = shops
                .stream()
                .map(shop -> CompletableFuture.supplyAsync(() ->
                        String.format("%s price is %.2f",
                                shop.getShopName(), shop.getPrice(product)))
                )
//                .map(future->future.thenApply())
//                .map(future->future.thenCompose())
                .map(CompletableFuture::join)
                .collect(toList());
//        List<String> returenData = collect.stream().map(CompletableFuture::join).collect(toList());
//        executor.shutdown();
        return collect;


    }
}
