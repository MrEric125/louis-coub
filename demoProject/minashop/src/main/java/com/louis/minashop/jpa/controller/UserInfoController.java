package com.louis.minashop.jpa.controller;

import com.louis.common.common.WrapMapper;
import com.louis.common.common.Wrapper;
import com.louis.minashop.jpa.entity2.UserInfo;
import com.louis.minashop.jpa.repository2.UserInfoRepository;
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
    public Wrapper add(@RequestBody UserInfo login) {

        UserInfo save = userInfoRepository.save(login);
        return WrapMapper.ok(save);
    }
    @RequestMapping(path = "/search", method = RequestMethod.GET)
    public Wrapper search() {

        List<UserInfo> all = userInfoRepository.findAll();
        return WrapMapper.ok(all);
    }
}
