package com.louis.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author louis
 * <p>
 * Date: 2019/11/21
 * Description:
 */
@Controller
@RequestMapping("/book")
public class BookController {

    @RequestMapping("/list.html")
    public String list() {
        return "boot/list";
    }

    @GetMapping("/add.html")
    public String add() {
        return "boo/add";
    }

    @GetMapping("/detail.html")
    public String detail() {
        return "book/detail";
    }


}
