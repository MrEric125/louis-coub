package com.louis.coub.escustomer.collect;


import com.louis.coub.escustomer.collect.thrift.BusinessLog;

/**
 * @author
 * @create 2019-04-08 10:34
 * @desc 业务日志处理
 **/
public class AmpLog {

    private static LogDataHanle logDataHanle;

    protected final static void regeditLogHandle(LogDataHanle logDataHanle){
        if (AmpLog.logDataHanle == null && logDataHanle != null){
            AmpLog.logDataHanle = logDataHanle;
        }else{
            //TODO 避免重复注册
        }
    }

    /**
     * 记录业务日志
     * @param key
     * @param field
     * @param log
     */
    public final static void addInfo(String key,String field,String log){
        if (AmpLog.logDataHanle != null){
            BusinessLog businessLog = new BusinessLog();
            businessLog.setDateTime(System.currentTimeMillis());
            businessLog.setField(field);
            businessLog.setKey(key);
            businessLog.setMessage(log);
            AmpLog.logDataHanle.addBusinessLog(businessLog);
        }else{
            //TODO 避免没有初始化调用
        }
    }

}
