package com.louis.minashop.controller;

import com.louis.minashop.entity.LoginUser;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author John·Louis
 * @date created on 2020/2/23
 * description:
 */
@RestController()
@RequestMapping("/user")
public class MinaLoginController {

    @PostMapping("/login")
    public String login(@RequestParam LoginUser loginUser) {
        return "login";

    }


}
