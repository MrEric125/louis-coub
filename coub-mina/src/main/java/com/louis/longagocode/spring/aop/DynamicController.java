package com.louis.longagocode.spring.aop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author John·Louis
 * @date created on 2020/2/7
 * description:
 */
@RestController()
@RequestMapping("/aop")
public class DynamicController {

    @Autowired
    private CglibDynamic cglibDynamic;

    @Autowired
    private JdkDynamicInterface jdkDynamicInterface;


    @RequestMapping("/cglib")
    public String cglibDynamic() {
        cglibDynamic.cglibDynamic();
        return "cglibDynamic";

    }

    @RequestMapping("/jdkDynamic")
    public String jdkDynamicInterface() {
        jdkDynamicInterface.jdkDynamic();
        return "jdkDynamicInterface";
    }
}
