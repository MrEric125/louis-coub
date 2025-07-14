package com.louis.minaProject.jpa.controller;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Maps;
import com.louis.common.common.HttpResult;
import com.louis.minaProject.jpa.entity2.UserInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

/**
 * @author John·Louis
 * @date created on 2020/2/23
 * description:
 * lettuce
 */
@Slf4j
@RestController
@RequestMapping("")
public class MinaController {

    @Autowired
    private ConfigurableEnvironment configurableEnvironment;

    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping("index")
    public HttpResult index() {
        MutablePropertySources propertySources = configurableEnvironment.getPropertySources();



        Map<String, String> maps = Maps.newHashMap();
        maps.put("source", JSON.toJSONString(propertySources, true));
        log.info("to" + maps);

        ObjectMapper objectMapper = new ObjectMapper();
        return HttpResult.ok(maps);

    }
    @RequestMapping("numJson")
    public HttpResult numJson() {
        Long number = 10000001462545303L;
        UserInfo userInfo = new UserInfo();
        userInfo.setId(number);
        return HttpResult.ok(JSON.toJSONString(userInfo));

    }



}
