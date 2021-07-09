package com.louis;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

    public String login(@RequestParam("userename") String usename, @RequestParam("pwd") String pwd) {
        return "sdd";
    }

}
