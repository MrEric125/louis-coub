package com.louis.minaProject;


import com.louis.config.SpringBeanUtils;
import com.louis.minaProject.jpa.entity.Login;
import com.louis.minaProject.jpa.repository.LoginRepository;
import com.louis.minaProject.jpa.service.UserService;
import org.springframework.aop.framework.AopContext;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
public class LoginService {

    @Resource
    private LoginRepository loginRepository;

    @Transactional(rollbackFor = Exception.class)
    public void addLogin(boolean rollback) {



        loginRepository.save(new Login());
        if (rollback) {
            throw new RuntimeException("rollback");
        }
    }


    public void create(boolean rollback, boolean useTransactional) {
        if (useTransactional) {
            LoginService bean = SpringBeanUtils.getBean(LoginService.class);
            bean.addLogin(rollback);

            return;
        }
        this.addLogin(rollback);

    }
}
