package com.louis.coub.escustomer.log;

import ch.qos.logback.classic.spi.IThrowableProxy;
import ch.qos.logback.classic.spi.LoggingEvent;
import ch.qos.logback.classic.spi.StackTraceElementProxy;
import ch.qos.logback.core.AppenderBase;
import com.louis.coub.escustomer.collect.AmpLogUtils;
import com.louis.coub.escustomer.collect.McThreadFactory;
import com.louis.coub.escustomer.collect.LogDataHandleImpl;
import com.louis.coub.escustomer.collect.LogDataHanle;
import com.louis.coub.escustomer.collect.thrift.LogInfo;

import java.util.ArrayList;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;

/**
 * @author louis
 * @date 2022/6/28
 */
public class AmpAppender extends AppenderBase<LoggingEvent> {
    private static final String TID = "tid";
    private static final String TRACEID = "traceId";
    public static final int DEFAULT_QUEUE_SIZE = 4000;
    int queueSize = 4000;
    private LogDataHanle logDataHanle;
    private BlockingQueue<LoggingEvent> blockingQueue;
    private ScheduledExecutorService scheduledExecutor;
    private String proCode;
    private String appCode;
    private String secretKey;
    private String line_separator = "\n";
    private String serverAddres;


    @Override
    protected void append(LoggingEvent loggingEvent) {
        if (!this.logDataHanle.isCollect()) {
            return;
        }
        loggingEvent.prepareForDeferredProcessing();
        loggingEvent.getCallerData();

        boolean offerSuccess = this.blockingQueue.offer(loggingEvent);

        if (!offerSuccess) {
            this.addWarn("日志队列已满");
        }


    }

    @Override
    public void start() {
        if (this.isStarted()) {
            return;
        }
        if (this.queueSize < 1) {
            this.addError("Invalid queue size [" + this.queueSize + "]");
        } else {
            this.queueSize = 4000;
        }
        if (this.serverAddres == null) {
            this.addError("mp log serverAddres is null");
            return;
        }
        this.scheduledExecutor = this.createScheduledExecutor();
        this.blockingQueue = new ArrayBlockingQueue(this.queueSize);
        this.logDataHanle = new LogDataHandleImpl(this.serverAddres, this.proCode, this.appCode, this.secretKey, this.scheduledExecutor);
        if (!this.logDataHanle.isCollect()) {
            this.addWarn("The application does not open log collection on mp platform");
        }

        this.createDataProcessTask();
        super.start();
    }
    private final ScheduledExecutorService createScheduledExecutor() {
        final ScheduledExecutorService pool = new ScheduledThreadPoolExecutor(5, new McThreadFactory("mc-log", false));
        Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
            public void run() {
                pool.shutdown();
                AmpAppender.this.addInfo("mp log worker start shutdown, mp log scheduledExecutor shutdown");
            }
        }, "mc-log-sdk-ShutdownHook"));
        return pool;
    }

    private void createDataProcessTask() {
        this.scheduledExecutor.submit(new Runnable() {
            public void run() {
                while(true) {
                    if (!Thread.currentThread().isInterrupted()) {
                        try {
                            LoggingEvent e = (LoggingEvent)AmpAppender.this.blockingQueue.take();
                            LogInfo logInfo = AmpAppender.this.createLogInfo(e);
                            AmpAppender.this.logDataHanle.addLogInfo(logInfo);
                            continue;
                        } catch (InterruptedException var3) {
                            AmpAppender.this.addWarn("mp log back thread interrupted");
                        } catch (Throwable var4) {
                            AmpAppender.this.addWarn("mp log take queue data on error:" + var4.getMessage());
                            continue;
                        }
                    }

                    return;
                }
            }
        });
    }

    protected LogInfo createLogInfo(LoggingEvent e) {
        LogInfo logInfo = new LogInfo();
        String log = e.getFormattedMessage();
        IThrowableProxy throwableProxy = e.getThrowableProxy();
        if (throwableProxy != null) {
            StringBuilder sb = new StringBuilder();
            sb.append(log).append(this.line_separator);
            sb.append(throwableProxy.getClassName()).append(":").append(throwableProxy.getMessage());
            StackTraceElementProxy[] traceElementProxies = throwableProxy.getStackTraceElementProxyArray();
            if (traceElementProxies != null) {
                sb.append(this.line_separator);
                for (StackTraceElementProxy stack : traceElementProxies) {
                    sb.append(stack.getSTEAsString()).append(this.line_separator);
                }
            }
            log = sb.toString();
        }

        logInfo.setLog(log);
        logInfo.setLevel(e.getLevel().levelStr);
        logInfo.setDateTime(e.getTimeStamp());
        logInfo.setThread(e.getThreadName());
        logInfo.setClassName(e.getLoggerName());
        String className;
        if (e.getMDCPropertyMap() != null) {
            className = (String)e.getMDCPropertyMap().get("tid");
            logInfo.setTid(className);
            String traceId = (String)e.getMDCPropertyMap().get("traceId");
            if (traceId != null) {
                log = "traceId:" + traceId + " " + log;
                logInfo.setLog(log);
            }
        }

        if (e.getThrowableProxy() != null) {
            className = e.getThrowableProxy().getClassName();
            logInfo.setEs(new ArrayList());
            logInfo.getEs().add(className);
        }

        int total = AmpLogUtils.getLogSize(logInfo);
        logInfo.setSize((long)total);
        return logInfo;
    }
}
