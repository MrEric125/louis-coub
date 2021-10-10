package com.louis.mybatis.tk;

import com.louis.common.common.HttpResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/insert")
    public HttpResult insert(@RequestBody UserInfo userInfo,Boolean throwable) throws Exception {
        UserInfo insertUser = userService.insertUser(userInfo, throwable);
        return HttpResult.ok(insertUser);

    }
}
