package com.louis.dubbo.spi;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.MDC;

import jakarta.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

/**
 * @author louis
 * @date 2022/5/25
 */
@SuppressWarnings("checkstyle:com.puppycrawl.tools.checkstyle.checks.design.FinalClassCheck")
@Slf4j
public class TraceIdUtils {

    private TraceIdUtils() {
    }

    /**
     * 日志配置文件中有一个traceId，这两者保持一致
     */

    public static final String TRACE_ID = "traceId";

    /**
     * 设置traceId
     */
    public static void setTraceId(String traceId) {
        if (StringUtils.isNotBlank(traceId)) {
            //将traceId放到MDC中
            MDC.put(TRACE_ID, traceId);
        }
    }

    /**
     * 获取traceId
     */
    public static String getTraceId() {
        //获取
        return MDC.get(TRACE_ID);

    }

    /**
     * 生成traceId
     */
    public static String generateTraceId() {
        String originTraceId = UUID.randomUUID().toString().replace("-", "");
        return originTraceId.substring(0, 24);
    }

    public static String genAndSetTraceId() {
        String traceId = generateTraceId();
        setTraceId(traceId);
        return traceId;
    }

    public static void removeTraceId() {
        MDC.remove(TRACE_ID);
    }

    public static Map<String, String> getMDCContactMap() {
        return MDC.getCopyOfContextMap();
    }

    /**
     * 异步情况设置全局traceIdMap信息
     * 如果有异步操作，请配合 {@link TraceIdUtils#removeTraceId()} 使用
     *
     * @param contextMap
     */
    public static void setContextMap(Map<String, String> contextMap) {
        if (MapUtils.isNotEmpty(contextMap)) {
            MDC.setContextMap(contextMap);
        }

    }

    public static void generateTraceIdToRequest(HttpServletRequest request) {
        try {
            String traceId;
            if (Objects.nonNull(request.getAttribute(TraceIdUtils.TRACE_ID))) {

                traceId = request.getAttribute(TraceIdUtils.TRACE_ID).toString();
                TraceIdUtils.setTraceId(traceId);
            } else {
                traceId = TraceIdUtils.genAndSetTraceId();
            }
            request.setAttribute(TraceIdUtils.TRACE_ID, traceId);
        } catch (Exception e) {
            String requestURI = request.getRequestURI();
            log.error("生成traceId异常:urI:{}", requestURI, e);
        }


    }


}
