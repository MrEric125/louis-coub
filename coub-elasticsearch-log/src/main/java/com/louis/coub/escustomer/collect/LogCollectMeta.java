package com.louis.coub.escustomer.collect;


import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Data
public class LogCollectMeta {

    private boolean success;
    /**
     * 当鉴权不成功时，此message为失败原因
     */
    private String message;
    /**
     * 返回可用的日志接收服务（包含当前机房的和所有的节点）
     */
    private LogServerInfo logServerInfo;
    /**
     * 此应用的配置的日志采集规则
     */
    private LogFilterRule logRule;
}
