package com.louis.dubbosourceproducer;

import com.louis.dubbo.spi.TraceIdUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.dubbo.common.constants.CommonConstants;
import org.apache.dubbo.common.extension.Activate;
import org.apache.dubbo.rpc.*;

/**
 * @author louis
 * @date 2022/7/26
 */
@Slf4j
@Activate(group ={CommonConstants.PROVIDER})
public class TraceProducerFilter implements Filter {
    @Override
    public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {

        String traceId = RpcContext.getContext().getAttachment("traceId");
        log.info("Trace producer receive traceId:{}", traceId);

        if (StringUtils.isNotBlank(traceId)) {
            TraceIdUtils.setTraceId(traceId);
        }
        return invoker.invoke(invocation);
    }

}
