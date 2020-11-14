package com.louis.controller;

import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * @author John·Louis
 * @date create in 2019/11/24
 * description:
 */
@RestController
public class LoginController {

    @RequestMapping(value = "/login-success", produces = {MediaType.TEXT_PLAIN_VALUE})
    public String loginSuccess() {
        return "login success";
    }

    public String getUsername() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Object principal = authentication.getPrincipal();
        if (principal != null) {
            if (principal instanceof UserDetails) {
                UserDetails userDetails = (UserDetails) principal;
                return userDetails.getUsername();
            }
        }
        return "匿名用戶";

    }

}
