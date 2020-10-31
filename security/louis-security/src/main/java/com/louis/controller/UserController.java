package com.louis.controller;

import com.louis.common.common.HttpResult;
import com.louis.common.common.HttpResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author louis
 * <p>
 * Date: 2019/11/18
 * Description:
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @RequestMapping("/insert")
    public HttpResult insert() {
        return HttpResult.ok("this is insert");
    }
    @RequestMapping("/select")
    public HttpResult select() {
        return HttpResult.ok("this is select");
    }


}
