package com.louis.remote.controller;

import com.louis.common.common.HttpResult;
import com.louis.common.common.HttpResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author louis
 * <p>
 * Date: 2019/10/16
 * Description:
 */
@RestController
@RequestMapping("/balance")
public class BalanceController {

    @RequestMapping("server")
    public HttpResult server() {
        return HttpResult.ok("this is server 2");
    }


}
