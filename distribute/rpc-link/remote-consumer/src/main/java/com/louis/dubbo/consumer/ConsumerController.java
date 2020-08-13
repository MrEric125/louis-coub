package com.louis.dubbo.consumer;

import com.louis.remote.UserContext;
import com.louis.remote.api.DefautDemoService;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.ServiceLoader;

/**
 * @author louis
 * <p>
 * Date: 2019/9/16
 * Description:
 */
@Slf4j
@RestController
//@RequestMapping("consumer")
public class ConsumerController {

    @Reference(retries = 0,timeout = 1000)
    private DefautDemoService defautDemoService;

    @GetMapping("/consumer")
    public Map<String, Object> consumer(HttpServletRequest request) {
        UserContext.putUsername(request.getLocalName());
        Map<String, Object> louis = defautDemoService.sayHello("louis");
        ServiceLoader<Robots> load = ServiceLoader.load(Robots.class);
        System.out.println("JAVA SPI");
        load.forEach(Robots::sayHello);

        return louis;

    }


}
