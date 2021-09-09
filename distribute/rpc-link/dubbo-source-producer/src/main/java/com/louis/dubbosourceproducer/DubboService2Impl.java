package com.louis.dubbosourceproducer;

import com.louis.dubbo.DubboService2;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.stereotype.Component;


@Service(interfaceClass = DubboService2.class, timeout = 5000)
@Component
public class DubboService2Impl implements DubboService2 {
    @Override
    public String dubboService2() {
        return "dubbo service2";
    }
}
