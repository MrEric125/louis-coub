package com.louis.remote.producer;

import com.google.common.collect.Maps;
import com.louis.remote.UserContext;
import com.louis.remote.api.DefautDemoService;
import org.springframework.beans.factory.annotation.Value;

import java.util.Map;

/**
 * @author louis
 * <p>
 * Date: 2019/9/16
 * Description:
 */
public class DefaultDemoServiceImpl implements DefautDemoService {


    @Value("${dubbo.application.name}")
    private String serviceName;

    public Map<String, Object> sayHello(String name) {

        String format = String.format("[%s] : Hello, %s", serviceName, name);
        Map<String, Object> maps = Maps.newHashMap();
        String username = UserContext.getUsername();

        maps.put("baseFormat", format);
        maps.put("username", username);

//        maps.put("cookie", request.getCookies().length > 0 ? request.getCookies()[0] : null);
//        maps.put("requestMethod", request.getMethod());
        return maps;
    }
}
