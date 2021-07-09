package com.source.spring.mvc;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/Test") //访问的url地址前缀，可以不写，写了就必须在方法url前面先加上class url 进行区分控制器
public class TestController
{
    //访问地址：http://localhost:8080/Test/returnSuccess
    @RequestMapping(value = "returnSuccess")    //实际访问的url地址
    public String returnSuccess() {
        return "/views/success";    //返回Views文件夹下的success.jsp页面
    }

    //访问地址：http://localhost:8080/Test/returnString
    @RequestMapping(value = "returnString", produces = {"text/plain;charset=UTF-8"})
    //produces用于解决返回中文乱码问题，application/json;为json解决中文乱码
    @ResponseBody       //用于返回字符串,不写即返回视图
    public String returnString() {
        return "hello return string 这是中文，并没有乱码";
    }

    @RequestMapping(value = "clickPost")
    public String clickPost(Long id) {
        System.out.println(id);
        return "index";
    }

    @RequestMapping(value = "clickGet/{id}/{userName}")
    public String clickGet(@PathVariable Long id, @PathVariable String userName) {
        System.out.println(id);
        System.out.println(userName);
        return "index";
    }
}