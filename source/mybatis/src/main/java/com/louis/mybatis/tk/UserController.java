package com.louis.mybatis.tk;

import com.alibaba.fastjson.JSON;
import com.louis.common.common.HttpResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserInfoMapper userInfoMapper;

    @RequestMapping("/insert")
    public HttpResult insert(@RequestBody UserInfo userInfo,Boolean throwable) throws Exception {
        userInfo.setJson(JSON.toJSONString(new IdName("張三", "李四")));
        UserInfo insertUser = userService.insertUser(userInfo, throwable);
        return HttpResult.ok(insertUser);
    }

    @RequestMapping("/select")
    private HttpResult select(Long id) {
        UserInfo userInfo = new UserInfo();
        userInfo.setId(id);

        List<UserInfo> select = userInfoMapper.select(userInfo);

        return HttpResult.ok(select);

    }
}
