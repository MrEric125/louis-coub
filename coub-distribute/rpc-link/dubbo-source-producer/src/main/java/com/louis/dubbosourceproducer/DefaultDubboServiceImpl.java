package com.louis.dubbosourceproducer;

import com.google.common.collect.Maps;
import com.louis.dubbo.DefaultDubboService;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author jun.liu
 * @since 2021/6/7 11:21
 */
@Service(interfaceClass = DefaultDubboService.class, timeout = 5000)
@Component
public class DefaultDubboServiceImpl implements DefaultDubboService {
    @Override
    public Map<String, Object> sayHello(String name) {
        Map<String, Object> map = Maps.newHashMap();
        map.put(name, name);
//        throw new RuntimeException("dd this is exception");
        System.out.println(map);
        return map;

    }
}
