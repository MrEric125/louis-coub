package com.louis.coub.escustomer.collect;


import com.louis.coub.escustomer.collect.thrift.LogInfo;

/**
 * @author
 * @create 2019-04-08 10:40
 * @desc
 **/
public class AmpLogUtils {

    public final static void regeditLogHandle(LogDataHanle logDataHanle){
        AmpLog.regeditLogHandle(logDataHanle);
    }

    /**
     * 计算日志大小
     * @param logInfo
     * @return
     */
    public final static int getLogSize(LogInfo logInfo){
        int total = logInfo.getLevel().getBytes().length;
        total = total + logInfo.getClassName().getBytes().length ;
        total = total + logInfo.getThread().getBytes().length ;
        total = total + logInfo.getLog().getBytes().length ;

        if (logInfo.getTid() != null){
            total = total + logInfo.getTid().getBytes().length ;
        }
        //时间为long型，所以字节为8个
        total = total + 8;
        return total;
    }
}
