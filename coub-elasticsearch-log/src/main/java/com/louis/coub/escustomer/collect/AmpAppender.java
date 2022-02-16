package com.louis.coub.escustomer.collect;

import ch.qos.logback.classic.spi.IThrowableProxy;
import ch.qos.logback.classic.spi.LoggingEvent;
import ch.qos.logback.classic.spi.StackTraceElementProxy;
import ch.qos.logback.core.AppenderBase;
import com.louis.coub.escustomer.collect.thrift.LogInfo;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.*;


/**
 * @author
 * @create 2019-03-11 17:12
 * @desc 日志接口
 **/
public class AmpAppender extends AppenderBase<LoggingEvent> {
    /**
     * 调用链生成的traceId
     */
    private static final String TID = "tid";
    /**
     * 业务自定义的traceId
     */
    private static final String TRACEID = "traceId";
    /**
     * The default buffer size.
     */
    public static final int DEFAULT_QUEUE_SIZE = 4000;

    int queueSize = DEFAULT_QUEUE_SIZE;

    private BlockingQueue<LoggingEvent> blockingQueue;

    private ScheduledExecutorService scheduledExecutor;

    private String proCode;

    private String appCode;

    private String secretKey;

    private String  line_separator = "\n";// System.getProperty("line.separator");

    public String getServerAddres() {
        return serverAddres;
    }

    public void setServerAddres(String serverAddres) {
        this.serverAddres = serverAddres;
    }

    private String serverAddres;

    private LogDataHanle logDataHanle;


    private final ScheduledExecutorService createScheduledExecutor() {

        final ScheduledExecutorService pool = new ScheduledThreadPoolExecutor(5, new DmcThreadFactory("dmc-log", false));
        // 退出时，终止监控数据发送线程
        Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
            @Override
            public void run() {
                pool.shutdown();
                addInfo("amp log worker start shutdown, amp log scheduledExecutor shutdown");
            }
        }, "dmc-log-sdk-ShutdownHook"));
        return pool;

    }
    @Override
    protected void append(LoggingEvent eventObject) {
        if (logDataHanle.isCollect()){
            eventObject.prepareForDeferredProcessing();
            eventObject.getCallerData();
            try {
                final String tid = null;
                if (eventObject.getMDCPropertyMap() != null){
                    if (!eventObject.getMDCPropertyMap().containsKey(TID)){
                        if (tid != null){
                            eventObject.getMDCPropertyMap().put(TID,tid);
                        }
                    }
                }else{
                    if (tid != null) {
                        Map<String, String> propertyMap = new HashMap<String, String>();
                        propertyMap.put(TID, tid);
                        eventObject.setMDCPropertyMap(propertyMap);
                    }
                }

                boolean success = blockingQueue.offer(eventObject);
                if (!success){
                    addWarn("日志队列满,故抛弃");
                    logDataHanle.addFiterData(LogFilterData.METRICS_INIT_QUEUE_FULL);
                }
            } catch (Exception e) {
                addWarn("线程中断",e);
            }
        }
    }

    @Override
    public void start() {
        if (!isStarted()){
            if (queueSize < 1) {
                addError("Invalid queue size [" + queueSize + "]");
            }else{
                queueSize = DEFAULT_QUEUE_SIZE;
            }

            if (this.serverAddres == null){
                addError("amp log serverAddres is null");
                return;
            }
            scheduledExecutor = createScheduledExecutor();
            blockingQueue = new ArrayBlockingQueue<>(queueSize);
            logDataHanle = new LogDataHandleImpl(this.serverAddres,proCode,appCode,secretKey,scheduledExecutor);
            if (!logDataHanle.isCollect()){
                addWarn("The application does not open log collection on amp platform");
            }

            createDataProcessTask();
            super.start();
        }
    }

    @Override
    public void stop() {
        if (isStarted()){
            addWarn("amp log worker stop...");
            scheduledExecutor.shutdownNow();
            if (blockingQueue != null && blockingQueue.size() > 0){
                addWarn("The amp log queue also has " + blockingQueue.size() + " logs waiting to be processed");
            }
            super.stop();
        }
    }


    private void createDataProcessTask(){
        //设置为守护线程，容易挂掉
        scheduledExecutor.submit(new Runnable() {
            @Override
            public void run() {
                while (!Thread.currentThread().isInterrupted()) {
                    try {
                        LoggingEvent e = blockingQueue.take();
                        LogInfo logInfo = createLogInfo(e);
                        logDataHanle.addLogInfo(logInfo);
                    } catch (InterruptedException ie) {
                        addWarn("amp log back thread interrupted");
                        break;
                    }catch (Throwable ie){
                        addWarn("amp log take queue data on error:" + ie.getMessage());
                    }
                }
            }
        });

    }

    protected LogInfo createLogInfo(LoggingEvent e) {
        LogInfo logInfo = new LogInfo();
        String log = e.getFormattedMessage();
        IThrowableProxy throwableProxy = e.getThrowableProxy();
        if (throwableProxy != null){
            StringBuilder sb = new StringBuilder();
            sb.append(log).append(line_separator);
            sb.append(throwableProxy.getClassName()).append(":").append(throwableProxy.getMessage());
            StackTraceElementProxy[] traceElementProxies = throwableProxy.getStackTraceElementProxyArray();
            if (traceElementProxies != null){
                sb.append(line_separator);
                for(StackTraceElementProxy stack:traceElementProxies){
                    sb.append(stack.getSTEAsString()).append(line_separator);
                }
            }
            log = sb.toString();
        }
        logInfo.setLog(log);
        logInfo.setLevel(e.getLevel().levelStr);
        logInfo.setDateTime(e.getTimeStamp());
        logInfo.setThread(e.getThreadName());
        logInfo.setClassName(e.getLoggerName());
        if (e.getMDCPropertyMap() != null){
            String tid = e.getMDCPropertyMap().get(TID);
            logInfo.setTid(tid);
            final String traceId = e.getMDCPropertyMap().get(TRACEID);
            if (traceId != null){
                log = TRACEID + ":" + traceId + " " + log;
                logInfo.setLog(log);
            }
        }

        if (e.getThrowableProxy()!=null){
            String className = e.getThrowableProxy().getClassName();
            logInfo.setEs(new ArrayList<String>());
            logInfo.getEs().add(className);
        }

        int total = AmpLogUtils.getLogSize(logInfo);
        logInfo.setSize(total);
        return logInfo;
    }

    public String getProCode() {
        return proCode;
    }

    public void setProCode(String proCode) {
        this.proCode = proCode;
    }

    public String getAppCode() {
        return appCode;
    }

    public void setAppCode(String appCode) {
        this.appCode = appCode;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

}