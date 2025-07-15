package com.louis.longagocode.spring.aop;

import org.springframework.stereotype.Service;

/**
 * @author John·Louis
 * @date created on 2020/2/7
 * description:
 */
@Service
public class JdkDynamicInterfaceImpl implements JdkDynamicInterface {

    @Override
    public void jdkDynamic() {
        System.out.println("jdkDynamic");
    }
}
