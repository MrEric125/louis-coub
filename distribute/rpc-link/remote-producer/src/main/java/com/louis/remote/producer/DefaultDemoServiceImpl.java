package com.louis.remote.producer;

import com.louis.remote.api.DefautDemoService;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Value;

/**
 * @author louis
 * <p>
 * Date: 2019/9/16
 * Description:
 */
@Service
public class DefaultDemoServiceImpl implements DefautDemoService {


    @Value("${dubbo.application.name}")
    private String serviceName;

    public String sayHello(String name) {
        String format = String.format("[%s] : Hello, %s", serviceName, name);
        return format;
    }
}
