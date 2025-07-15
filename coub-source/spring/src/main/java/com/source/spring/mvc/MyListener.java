package com.source.spring.mvc;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * @author Louis
 * @date created on 2020/11/28
 * description:
 */
@Component
@Slf4j
public class MyListener implements ApplicationListener<MyEvent> {


    @Override
    public void onApplicationEvent(MyEvent event) {
        log.info("监听=={}", JSON.toJSONString(event, true));
    }

}
