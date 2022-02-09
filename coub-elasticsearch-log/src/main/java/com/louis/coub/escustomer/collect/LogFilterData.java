package com.louis.coub.escustomer.collect;

public class LogFilterData {
    /**
     * 日志抛弃指标:超过单行最大限制
     */
    public static final String METRICS_LINEMAX = "lines";
    /**
     * 日志抛弃指标:ip过滤
     */
    public static final String METRICS_IP = "filterIp";
    /**
     * 日志抛弃指标:指定类过滤
     */
    public static final String METRICS_CLASSNAME = "className";
    /**
     * 日志抛弃指标:指定类采集级别过滤
     */
    public static final String METRICS_CLASSLEVEL = "classLevel";

    /**
     * 日志抛弃指标:日志规则中的级别高于日志的级别而抛弃的日志数量
     */
    public static final String METRICS_LEVEL = "level";
    /**
     * 日志抛弃指标:日志因为队列满而抛弃的日志数量
     */
    public static final String METRICS_QUEUE_FULL = "queueFull";
    /**
     * 日志抛弃指标:日志因为初始化队列满而抛弃的日志数量
     */
    public static final String METRICS_INIT_QUEUE_FULL = "initQueueFull";
}
