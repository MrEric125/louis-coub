package com.louis.minashop.jpa.controller;

import com.louis.common.common.WrapMapper;
import com.louis.common.common.Wrapper;
import com.louis.minashop.jpa.entity.Login;
import com.louis.minashop.jpa.repository.LoginRepository;
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
    public Wrapper login(@RequestParam(required = false) Login loginUser, @RequestParam Long sprite) {

        double x=loginUser.getId() / sprite;
        log.info("{},{},{}", loginUser.getId(), loginUser.getCode(), sprite);

        return WrapMapper.ok(x);
    }

    @RequestMapping(path = "/add", method = RequestMethod.POST)
    public Wrapper add(@RequestBody Login login) {

        Login save = repository.save(login);
        return WrapMapper.ok(save);
    }
    @RequestMapping(path = "search", method = RequestMethod.GET)
    public Wrapper search(Login login) {

        List<Login> all = repository.findAll();
        return WrapMapper.ok(all);
    }


}
