package com.spring.transaction.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author louis
 * <p>
 * Date: 2020/1/3
 * Description:
 */
@RequestMapping("/trans")
@RestController
public class TransactionController {


    @PutMapping
    public String save() {
        return "ok";
    }


    @PostMapping
    public String update() {

        return "ok";
    }

}
