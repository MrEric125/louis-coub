package com.louis.remote.controller;

import com.louis.common.common.WrapMapper;
import com.louis.common.common.Wrapper;
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
    public Wrapper server() {
        return WrapMapper.ok("server 1111111111111111111");
    }

}
