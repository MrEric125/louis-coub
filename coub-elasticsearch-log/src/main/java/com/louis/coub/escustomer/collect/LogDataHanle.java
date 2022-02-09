package com.louis.coub.escustomer.collect;

import com.louis.coub.escustomer.collect.thrift.BusinessLog;
import com.louis.coub.escustomer.collect.thrift.LogInfo;

public interface LogDataHanle {

    /**
     * 将日志放入到日志处理器中
     * @param logInfo
     * @return
     */
    boolean addLogInfo(LogInfo logInfo);
    /**
     * 将业务日志放入到日志处理器中
     * @param log
     * @return
     */
    boolean addBusinessLog(BusinessLog log);


    boolean isCollect();

    /**
     * 因为队列满而导致的日志丢失
     * @param metrics
     */
    void addFiterData(String metrics);

}
