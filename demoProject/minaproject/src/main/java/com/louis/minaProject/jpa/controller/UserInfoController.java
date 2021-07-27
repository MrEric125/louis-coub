package com.louis.minaProject.jpa.controller;

import com.louis.common.common.HttpResult;
import com.louis.minaProject.jpa.entity2.UserInfo;
import com.louis.minaProject.jpa.repository2.UserInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("/userInfo")
public class UserInfoController {

    @Autowired
    private UserInfoRepository userInfoRepository;


    @RequestMapping(path = "/add", method = RequestMethod.POST)
    public HttpResult add(@RequestBody UserInfo login) {

        UserInfo save = userInfoRepository.save(login);
        return HttpResult.ok(save);
    }
    @RequestMapping(path = "/search", method = RequestMethod.GET)
    public HttpResult search() {

        List<UserInfo> all = userInfoRepository.findAll();
        return HttpResult.ok(all);
    }
}
