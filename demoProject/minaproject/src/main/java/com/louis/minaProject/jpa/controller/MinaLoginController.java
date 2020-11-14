package com.louis.minaProject.jpa.controller;

import com.louis.common.common.HttpResult;
import com.louis.common.common.HttpResult;
import com.louis.minaProject.jpa.entity.Login;
import com.louis.minaProject.jpa.repository.LoginRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author John·Louis
 * @date created on 2020/2/23
 * description:
 */
@Slf4j
@RestController()
@RequestMapping("/login")
public class MinaLoginController {

    @Autowired
    private LoginRepository repository;



    @PostMapping("/login")
    public HttpResult login(@RequestParam(required = false) Login loginUser, @RequestParam Long sprite) {

        double x=loginUser.getId() / sprite;
        log.info("{},{},{}", loginUser.getId(), loginUser.getCode(), sprite);

        return HttpResult.ok(x);
    }

    @RequestMapping(path = "/add", method = RequestMethod.POST)
    public HttpResult add(@RequestBody Login login) {

        Login save = repository.save(login);
        return HttpResult.ok(save);
    }
    @RequestMapping(path = "search", method = RequestMethod.GET)
    public HttpResult search(Login login) {

        List<Login> all = repository.findAll();
        return HttpResult.ok(all);
    }


}
