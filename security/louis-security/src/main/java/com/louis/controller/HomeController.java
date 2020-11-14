package com.louis.controller;

import com.louis.common.common.HttpResult;
import com.louis.common.common.HttpResult;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * @author louis
 * <p>
 * Date: 2019/11/18
 * Description:
 */
@Controller
public class HomeController {

    @RequestMapping("/")
    @ResponseBody
    public HttpResult homePage() {
        return HttpResult.ok("this is home page");
    }

    @RequestMapping("/home")
    @ResponseBody
    public HttpResult home() {
        return HttpResult.ok("this is home ");
    }




}
