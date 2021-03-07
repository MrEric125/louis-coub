package com.louis.minaProject.jpa.controller;

import com.google.common.collect.Lists;
import com.louis.common.common.HttpResult;
import com.louis.common.common.HttpResult;
import com.louis.minaProject.jpa.entity.Login;
import com.louis.minaProject.jpa.repository.LoginRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

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
    public HttpResult add(Long iterator) {
        List<Login> loginList = Lists.newArrayList();
        for (int i = 0; i < iterator; i++) {
            Login login =Login.builder()
                    .code(this.uuidBuilder())
                    .encryptedData(this.uuidBuilder())
                    .userInfo(this.uuidBuilder())
                    .WxAppId(this.uuidBuilder())
                    .token(this.uuidBuilder())
                    .signature(this.uuidBuilder())

                    .build();
            loginList.add(login);
        }
        try {
            repository.saveAll(loginList);
        } catch (Exception e) {
            log.error("批量插入数据异常", e);
        }
        return HttpResult.ok();
    }
    @RequestMapping(path = "search", method = RequestMethod.GET)
    public HttpResult search(Login login) {

        List<Login> all = repository.findAll();
        return HttpResult.ok(all);
    }

    private String uuidBuilder() {
        UUID uuid = UUID.randomUUID();
        return uuid.toString();
    }
    

}
