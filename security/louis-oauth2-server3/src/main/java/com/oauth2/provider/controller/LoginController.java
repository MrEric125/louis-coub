package com.oauth2.provider.controller;

import com.louis.common.common.WrapMapper;
import com.louis.common.common.Wrapper;
import com.oauth2.provider.dto.UserDto;
import com.oauth2.provider.service.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * @author John·Louis
 * @date create in 2019/11/24
 * description:
 */
@RestController
public class LoginController {

    @Autowired
    private UserService userService;

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

    @RequestMapping("/user/login")
    public Wrapper userLogin(@RequestBody UserDto userDto) {

        return WrapMapper.ok();
    }

}
