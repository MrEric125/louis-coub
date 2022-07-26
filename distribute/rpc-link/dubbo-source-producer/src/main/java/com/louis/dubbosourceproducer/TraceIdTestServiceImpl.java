package com.louis.dubbosourceproducer;

import com.louis.dubbo.TraceIdTestService;
import com.louis.dubbo.spi.TraceIdUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.stereotype.Component;

/**
 * @author louis
 * @date 2022/7/26
 */
@Slf4j
@Component
@Service
public class TraceIdTestServiceImpl implements TraceIdTestService {

    @Override
    public String doTraceId(String param) {
        log.info("traceId test param:{}", param);
        return TraceIdUtils.getTraceId();
    }
}
