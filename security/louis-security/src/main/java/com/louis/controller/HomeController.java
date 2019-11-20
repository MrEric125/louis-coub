package com.louis.controller;

import com.google.common.collect.Maps;
import com.louis.common.common.WrapMapper;
import com.louis.common.common.Wrapper;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * @author louis
 * <p>
 * Date: 2019/11/18
 * Description:
 */
@RestController
public class HomeController {

    @RequestMapping("/")
    public Wrapper homePage() {
        return WrapMapper.ok("this is home page");
    }


}
