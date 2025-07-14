package com.louis.dubbosourceconsumer;

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
@Activate(group ={CommonConstants.CONSUMER})
public class TraceFilter implements Filter {
    @Override
    public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {

        String traceId = TraceIdUtils.getTraceId();

        log.info("TraceFilter:{}", traceId);
        if (StringUtils.isNotBlank(traceId)) {
            RpcContext.getContext().setAttachment("traceId", traceId);
        }





        return invoker.invoke(invocation);
    }

}
