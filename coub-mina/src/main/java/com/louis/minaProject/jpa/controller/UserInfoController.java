package com.louis.minaProject.jpa.controller;

import com.alibaba.fastjson.JSON;
import com.louis.common.common.HttpResult;
import com.louis.minaProject.jpa.entity2.UserInfo;
import com.louis.minaProject.jpa.repository2.UserInfoRepository;
import com.louis.minaProject.jpa.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author John·Louis
 * @date created on 2020/3/13
 * description:
 */
@RestController
@RequestMapping("/user")
public class UserInfoController {

    @Autowired
    private UserInfoRepository userInfoRepository;

    @Autowired
    private UserService userService;

    @Transactional(rollbackFor = Exception.class)
    @RequestMapping(path = "/insert", method = RequestMethod.POST)
    public HttpResult add(@RequestBody UserInfo login, Boolean throwable) throws Exception {
        String s = JSON.toJSONString(login);
        login.setJson(s);
        UserInfo save = userInfoRepository.save(login);
        if (throwable) {
            throw new RuntimeException("插入数据逻辑报错");
        }

        return HttpResult.ok(save);
    }

    @RequestMapping(path = "/search", method = RequestMethod.GET)
    public HttpResult search() {

        List<UserInfo> all = userInfoRepository.findAll();
        return HttpResult.ok(all);
    }


    @RequestMapping(path = "/txDemo", method = RequestMethod.GET)
    public HttpResult txDemo() {
        userService.tx1();
        return HttpResult.ok();
    }

}
