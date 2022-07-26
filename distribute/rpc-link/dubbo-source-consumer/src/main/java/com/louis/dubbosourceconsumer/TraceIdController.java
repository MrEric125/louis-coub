package com.louis.dubbosourceconsumer;

import com.louis.dubbo.TraceIdTestService;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author louis
 * @date 2022/7/26
 */
@Slf4j
@RestController
public class TraceIdController {


    @Reference
    private TraceIdTestService traceIdTestService;


    @RequestMapping("/traceId")
    public void traceId() {

        String traceId = traceIdTestService.doTraceId(TraceIdUtils.getTraceId());

        log.info("traceId:  {}", traceId);

    }
}
