package com.louis.minaProject.jpa.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author John·Louis
 * @date created on 2020/2/23
 * description:
 * lettuce
 */
@RestController
@RequestMapping("")
public class MinaController {

    @RequestMapping("index")
    public String index() {

        return "hello world";

    }

}
