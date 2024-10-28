package com.source.spring.mvc;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;

@RestController
@RequestMapping(value = "api")
public class TestController {

    @PostConstruct
    public void init() {
        System.out.println("init");
    }

    @RequestMapping(value = "returnSuccess")    //实际访问的url地址
    public String returnSuccess() {
        return "success";    //返回Views文件夹下的success.jsp页面
    }

    @RequestMapping(value = "/returnString")
    @ResponseBody       //用于返回字符串,不写即返回视图
    public String returnString() {
        return "hello return string 这是中文，并没有乱码";
    }

    @RequestMapping(value = "/")
    public String clickPost(Long id) {
        System.out.println(id);
        return "index";
    }

    @RequestMapping(value = "/clickGet/{id}/{userName}")
    @ResponseBody
    public String clickGet(@PathVariable Long id, @PathVariable String userName) {
        System.out.println(id);
        System.out.println(userName);
        return "success";
    }

    @RequestMapping("/clickPost")
    @ResponseBody
    public String clickPost(String id) {
        System.out.println(id);
        return id;
    }
}