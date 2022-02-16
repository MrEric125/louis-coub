package com.louis.coub.escustomer.collect;

import ch.qos.logback.classic.Level;
import com.alibaba.fastjson.JSON;
import com.louis.coub.escustomer.collect.thrift.*;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author
 * @create 2019-03-13 18:04
 * @desc
 **/
public class LogDataHandleImpl implements LogDataHanle {

    private AppInfo appInfo;

    private LogFilterRule logFilterRule;

    private LogServerInfo logServerInfo;

    private Random rd = new Random();

    private final String serverAddres;

    private final String url = "/log/authAndRule";

    private final Long logLineMaxSize = 1024 * 500L;

    private static final long MAX_TOTAL = 10 * 1024 * 1024L;

    private final BlockingQueue<LogInfo> logQueue = new ArrayBlockingQueue<>(5000);

    private final BlockingQueue<BusinessLog> businessLogQueue = new ArrayBlockingQueue<>(5000);

    private final ConcurrentHashMap<String,AtomicInteger> fiterDataMap = new ConcurrentHashMap();

    private final ScheduledExecutorService scheduledExecutor;

    public LogDataHandleImpl(String serverAddres,String proCode, String appCode, String secretKey,ScheduledExecutorService scheduledExecutor){
        this.scheduledExecutor = scheduledExecutor;
        this.appInfo = new AppInfo();
        this.appInfo.setProCode(proCode);
        this.appInfo.setAppCode(appCode);
        this.appInfo.setSecretKey(secretKey);
        this.appInfo.setIp(MonitorUtils.getLocalIP());
        this.appInfo.setInstanceCode(MonitorUtils.getJvmInstanceCode());
        this.appInfo.setPid(MonitorUtils.getPid());
        this.serverAddres = serverAddres;

        initScheduledTask();

        auth();

        AmpLogUtils.regeditLogHandle(this);

        Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
            @Override
            public void run() {
                addInfo("amp log sdk start shutdown....");
                uploadAppLog();
                uploadBusinessLog();
                uploadFilterData();
                addInfo("amp log sdk shutdown");
            }
        }, "amp-log-ShutdownHook"));

        initFiterDataMap();
    }

    /**
     * 初始化定时任务
     */
    private void initScheduledTask() {
        scheduledExecutor.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                try {
                    uploadAppLog();
                } catch (Throwable e) {
                    addInfo("amp-log-sdk Failed to upload application log:" + e.getMessage());
                }
            }
        }, 2L, 2L, TimeUnit.SECONDS);

        scheduledExecutor.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                try {
                    uploadBusinessLog();
                } catch (Throwable e) {
                    addInfo("amp-log-sdk Failed to upload business log:" + e.getMessage());
                }
            }
        }, 5L, 2L, TimeUnit.SECONDS);

        scheduledExecutor.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                try {
                    auth();
                } catch (Throwable e) {
                    addInfo("Failure of Log Acquisition Authentication in amp:" + e.getMessage());
                }
            }
        }, 10L, 10L, TimeUnit.SECONDS);
        scheduledExecutor.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                try {
                    uploadFilterData();
                } catch (Throwable e) {
                    addInfo("Failure to report amp log filter count:" + e.getMessage());
                }
            }
        }, 60L, 60L, TimeUnit.SECONDS);

    }

    private void initFiterDataMap(){
        fiterDataMap.put(LogFilterData.METRICS_IP,new AtomicInteger(0));
        fiterDataMap.put(LogFilterData.METRICS_CLASSLEVEL,new AtomicInteger(0));
        fiterDataMap.put(LogFilterData.METRICS_LINEMAX,new AtomicInteger(0));
        fiterDataMap.put(LogFilterData.METRICS_CLASSNAME,new AtomicInteger(0));
        fiterDataMap.put(LogFilterData.METRICS_LEVEL,new AtomicInteger(0));
        fiterDataMap.put(LogFilterData.METRICS_QUEUE_FULL,new AtomicInteger(0));
        fiterDataMap.put(LogFilterData.METRICS_INIT_QUEUE_FULL,new AtomicInteger(0));
    }

    private void uploadFilterData() {

        Map<String,Integer> data = new HashMap<>();

        for(String key:fiterDataMap.keySet()){
            Integer v = fiterDataMap.get(key).get();
            if (v > 0){
                data.put(key,v);
                fiterDataMap.get(key).addAndGet(-v);
            }
        }

        if (data.size() > 0){
            final String dataTime = DateTimeUtils.formatDateTime(System.currentTimeMillis(),DateTimeUtils.DATE_yyyyMMddHHmm);
            LogFilterStatistic filterData = new LogFilterStatistic();
            filterData.setProCode(this.appInfo.getProCode());
            filterData.setAppCode(this.appInfo.getAppCode());
            filterData.setInstCode(this.appInfo.getInstanceCode());
            filterData.setIp(this.appInfo.getIp());
            Long date = DateTimeUtils.dateTimeToLong(dataTime,DateTimeUtils.DATE_yyyyMMddHHmm);
            filterData.setDateTime(date);
            filterData.setData(data);
            sendFilterData(filterData);
        }

    }

    @Override
    public boolean addLogInfo(LogInfo logInfo){
        if (filterRuleCheck(logInfo)){
            try {
                boolean success = logQueue.offer(logInfo);
                if (!success){
                    success = logQueue.offer(logInfo,2,TimeUnit.MILLISECONDS);
                }
                if (!success){
                    addFiterData(LogFilterData.METRICS_QUEUE_FULL);
                }
                return success;
            } catch (InterruptedException e) {
               addInfo("add app log to amp queue fail,thread interrupted");
            }
        }
        return false;
    }

    public boolean addBusinessLog(BusinessLog log){
        try {
            boolean success = businessLogQueue.offer(log);
            if (!success){
                return businessLogQueue.offer(log,2,TimeUnit.SECONDS);
            }
        } catch (InterruptedException e) {
            addInfo("add business log to amp queue fail,thread interrupted");

        }
        return false;
    }

    @Override
    public void addFiterData(String metrics){
        fiterDataMap.get(metrics).incrementAndGet();
    }

    private void addFiterData(String metrics,int count){
        fiterDataMap.get(metrics).addAndGet(count);
    }
    private boolean filterRuleCheck(LogInfo e) {
        if (!logFilterRule.isCollect()){
            return false;
        }

        long logSize = e.getSize();

        if (logFilterRule == null){
            if (logSize > logLineMaxSize ){
                addFiterData(LogFilterData.METRICS_LINEMAX);
                return false;
            }
        }else {
            if (logFilterRule.getFilterIpList() != null){
                if (logFilterRule.getFilterIpList().contains(MonitorUtils.getLocalIP())){
                    addFiterData(LogFilterData.METRICS_IP);
                    return false;
                }
            }

            String className = e.getClassName();

            if (logFilterRule.getFilterClassList() != null ){
                if (logFilterRule.getFilterClassList().contains(className)){
                    addFiterData(LogFilterData.METRICS_CLASSNAME);
                    return false;
                }
            }

            long maxSize = logFilterRule.getLogLineMaxSize() * 1024L;

            if (logSize > maxSize){
                addFiterData(LogFilterData.METRICS_LINEMAX);
                return false;
            }

            Level eLevel = Level.valueOf(e.getLevel());
            if (logFilterRule.getClassLevel() != null){
                final String level = logFilterRule.getClassLevel().get(className);
                if (level != null){
                    final Level logLevel = Level.valueOf(level);
                    if (eLevel.toInt() < logLevel.toInt() ){
                        addFiterData(LogFilterData.METRICS_CLASSLEVEL);
                        return false;
                    }else{
                        return true;
                    }
                }
            }

            final String sRuleLevel = logFilterRule.getLevel();
            Level ruleLevel = Level.valueOf(sRuleLevel);
            if (eLevel.toInt() < ruleLevel.toInt()  ){
                addFiterData(LogFilterData.METRICS_LEVEL);
                return false;
            }

        }

        return true;
    }

    private boolean auth()  {
        final String authUrl = this.serverAddres + url;
        HttpURLConnection conn = null;
        InputStream is = null;
        LogCollectMeta logCollectMeta;
        final String sUrl = String.format("%s?instanceCode=%s&proCode=%s&appCode=%s&ip=%s&secretKey=%s&pid=%s",
                authUrl, appInfo.getInstanceCode(), appInfo.getProCode(), appInfo.getAppCode(),appInfo.getIp(),appInfo.getSecretKey(),appInfo.getPid());
        try {


            URL mURL = new URL(sUrl);
            conn = (HttpURLConnection) mURL.openConnection();

            conn.setRequestMethod("GET");
            conn.setReadTimeout(5000);
            conn.setConnectTimeout(5000);
            conn.addRequestProperty("token", "dmc-monitor");

            int responseCode = conn.getResponseCode();
            if (responseCode == 200) {

                is = conn.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
                StringBuffer sb = new StringBuffer();

                String line;
                while ((line = reader.readLine()) != null) {
                    sb.append(line);
                }
                String text = sb.toString();
                LogFilterRule temp = null;
                if (!"{}".equals(text)) {
                    logCollectMeta = JSON.parseObject(text, LogCollectMeta.class);
                    if (logCollectMeta != null) {
                        if (logCollectMeta.isSuccess()){
                            temp = logCollectMeta.getLogRule();
                            logServerInfo = logCollectMeta.getLogServerInfo();
                        }else{
                            addInfo("get log collect rule fail,message:" + logCollectMeta.getMessage());
                        }
                    }
                }else{
                    return false;
                }

                if (temp == null){
                    temp = new LogFilterRule();
                    temp.setCollect(false);
                }

                if (this.logFilterRule != null){
                    if (logFilterRule.isCollect() != temp.isCollect()){
                        addInfo("The log collection status has changed, and the current collection switch is:" + logFilterRule.isCollect() + ",变更后为:" + temp.isCollect());
                    }
                    if (temp.isCollect()){
                        long maxSize = temp.getLogLineMaxSize() * 1024L;
                        if (maxSize > logLineMaxSize){
                            temp.setLogLineMaxSize(500L);
                        }
                    }
                }

                logFilterRule = temp;
            }

        } catch (Exception ex) {
            addInfo("get log collect rule fail, url:" + sUrl + ", error:" + ex.getMessage());
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
            }
            if (conn != null) {
                conn.disconnect();
            }
        }

        if (this.logFilterRule != null){
            return this.logFilterRule.isCollect();
        }else{
            return false;
        }
    }

    private void addInfo(String info){
        System.out.println("[INFO] " + DateTimeUtils.formatDateTime() +" [" + Thread.currentThread().getName() + "] " + "[amp log sdk] " + info);
    }

    @Override
    public boolean isCollect() {
        if (this.logFilterRule != null){
            return this.logFilterRule.isCollect();
        }else{
            return false;
        }
    }


    private void uploadAppLog(){
        int size = logQueue.size();
        if (size > 0){
            while (true){
                int max = 1000;
                size = logQueue.size();
                if (size == 0){
                    break;
                }
                if (size < max){
                    max = size;
                    List<LogInfo> logInfoList = new ArrayList<>(max);
                    logQueue.drainTo(logInfoList,max);
                    uploadLogData(logInfoList);
                    break;
                }else{
                    List<LogInfo> logInfoList = new ArrayList<>(max);
                    logQueue.drainTo(logInfoList,max);
                    uploadLogData(logInfoList);
                }
            }
        }
    }

    private void uploadBusinessLog(){
        int size = businessLogQueue.size();
        if (size > 0){
            while (true){
                int max = 500;
                size = businessLogQueue.size();
                if (size == 0){
                    break;
                }
                if (size < max){
                    max = size;
                    List<BusinessLog> logInfoList = new ArrayList<>(max);
                    businessLogQueue.drainTo(logInfoList,max);
                    uploadBusinessLogData(logInfoList);
                    break;
                }else{
                    List<BusinessLog> logInfoList = new ArrayList<>(max);
                    businessLogQueue.drainTo(logInfoList,max);
                    uploadBusinessLogData(logInfoList);
                }
            }
        }
    }

    private void uploadBusinessLogData(List<BusinessLog> logInfoList) {
        BusinessLogData logData = new BusinessLogData();
        logData.setProCode(this.appInfo.getProCode());
        logData.setAppCode(this.appInfo.getAppCode());
        logData.setIp(this.appInfo.getIp());
        logData.setInstCode(this.appInfo.getInstanceCode());
        logData.setPid(this.appInfo.getPid());
        logData.setLogList(logInfoList);
        Long totalByte = 0L;
        for(BusinessLog logInfo:logInfoList){
            totalByte += logInfo.getSize();
        }

        logData.setTotalSize(totalByte);

        boolean success = sendData(logData);
        if (!success){
            for(int i=0;i<3;i++){
                success = sendData(logData);
                if (success){
                    break;
                }
            }
        }
    }
    private void uploadLogData(List<LogInfo> logInfoList) {
        if (logFilterRule.isCollect()) {
            LogData logData = new LogData();
            logData.setProCode(this.appInfo.getProCode());
            logData.setAppCode(this.appInfo.getAppCode());
            logData.setIp(this.appInfo.getIp());
            logData.setInstCode(this.appInfo.getInstanceCode());
            logData.setPid(this.appInfo.getPid());

            Long totalByte = 0L;
            List<LogInfo> dataList = new ArrayList<>();
            for (LogInfo logInfo : logInfoList) {
                totalByte += logInfo.getSize();
                dataList.add(logInfo);
                if (totalByte > MAX_TOTAL) {
                    logData.setLogList(dataList);
                    logData.setTotalSize(totalByte);

                    uploadData(logData);

                    totalByte = 0L;
                    dataList.clear();
                }
            }

            if (totalByte > 0 && dataList.size() > 0){
                logData.setLogList(dataList);
                logData.setTotalSize(totalByte);
                uploadData(logData);
            }

        }else{
            addInfo("amp log discard data app " + this.appInfo.getAppCode() + " application does not open log collection");
        }

    }

    private boolean uploadData(LogData logData){
        boolean success = sendData(logData);
        if (!success) {
            for (int i = 0; i < 2; i++) {
                success = sendData(logData);
                if (success) {
                    break;
                }
            }
        }
        return success;
    }

    private boolean sendData(LogData logData) {

        LogServer logServer;
        try{
            logServer = getLogServer();
        }catch (NullPointerException ex){
            addInfo("amp log server not find,Log data will be lost,error: " + ex.getMessage());
            return false;
        }

        TTransport transport = null;
        try {
            transport = getTransport(logServer);
            TProtocol protocol = new TBinaryProtocol(transport);
            LogDataServer.Client dataClient = new LogDataServer.Client(protocol);

            transport.open();

            dataClient.logDataHandle(logData);
            return true;
        } catch (Exception ex) {
            addInfo("uploading log data to log server fail,on error:" + ex.getMessage());
            checkSocket(logServer);
            return false;
        } finally {
            if (null != transport) {
                transport.close();
            }
        }
    }

    private boolean sendFilterData(LogFilterStatistic logData) {

        LogServer logServer;
        try{
            logServer = getLogServer();
        }catch (NullPointerException ex){
            addInfo("amp log server not find,Log filtering statistics will be lost,error: " + ex.getMessage());
            return false;
        }

        TTransport transport = null;
        try {
            transport = getTransport(logServer);
            TProtocol protocol = new TBinaryProtocol(transport);
            LogDataServer.Client dataClient = new LogDataServer.Client(protocol);

            transport.open();

            dataClient.filterDataHandle(logData);
            return true;
        } catch (Exception ex) {
            addInfo("An error occurred while uploading  log filter data, error:" + ex.getMessage());
            checkSocket(logServer);
            return false;
        } finally {
            if (null != transport) {
                transport.close();
            }
        }
    }

    private synchronized LogServer getLogServer(){

        final List<LogServer> currentComputerServer = logServerInfo.getCurrentComputer();

        final List<LogServer> allServer = logServerInfo.getAll();

        LogServer logServer;

        if (logServerInfo.getUploadMode() == LogServerInfo.UPLOAD_MODE_CURRENT){
            //current mode
            logServer = getRandomServer(currentComputerServer);
        }else if (logServerInfo.getUploadMode() == LogServerInfo.UPLOAD_MODE_RANDOM){
            //random mode
            logServer = getRandomServer(allServer);
        }else if (logServerInfo.getUploadMode() == LogServerInfo.UPLOAD_MODE_MIND){
            //mind mode
            if (currentComputerServer == null || currentComputerServer.size() == 0 ){
                logServer = getRandomServer(allServer);
            }else{
                if (currentComputerServer.size() >= logServerInfo.getServiceCount()){
                    logServer = getRandomServer(currentComputerServer);
                }else{
                    logServer = getRandomServer(allServer);
                }
            }
        }else{
            logServer = getRandomServer(allServer);
        }

        return logServer;
    }

    private LogServer getRandomServer(List<LogServer> serverList){
        LogServer logServer;
        if (serverList != null && serverList.size() > 0){
            if (serverList.size() == 1){
                logServer = serverList.get(0);
            }else{
                int index = rd.nextInt(serverList.size());
                if (index == serverList.size()){
                    index = index -1;
                }
                logServer = serverList.get(index);
            }

            return logServer;
        }else{
            throw new NullPointerException("not find log server");
        }
    }

    private boolean sendData(BusinessLogData logData) {


        LogServer logServer;
        try{
            logServer = getLogServer();
        }catch (NullPointerException ex){
            addInfo("amp log server not find,business Log data will be lost,error: " + ex.getMessage());
            return false;
        }

        TTransport transport = null;
        try {
            transport = getTransport(logServer);
            TProtocol protocol = new TBinaryProtocol(transport);
            LogDataServer.Client dataClient = new LogDataServer.Client(protocol);

            transport.open();

            dataClient.businessLogHadle(logData);
            return true;
        } catch (Exception ex) {
            addInfo("Failed to report business log data. The error is:" + ex.getMessage());
            checkSocket(logServer);
            return false;
        } finally {
            if (null != transport) {
                transport.close();
            }
        }
    }

    private synchronized void checkSocket(LogServer logServer) {
        InetAddress inetAddress;
        Socket socket = new Socket();
        try {
            inetAddress = InetAddress.getByName(logServer.getIp());
            SocketAddress socketAddress = new InetSocketAddress(inetAddress, logServer.getPort());
            socket.connect(socketAddress, 500);
            addInfo(String.format("data server host:%s port:%d is available", logServer.getIp(),logServer.getPort()));
        } catch (Exception e) {
            addInfo(String.format("data server:host:%s port:%d is not available,error：%s", logServer.getIp(),logServer.getPort(), e.getMessage()));

            if (logServerInfo.getCurrentComputer() != null){
                for(LogServer server:logServerInfo.getCurrentComputer()){
                    if (server.getIp().equalsIgnoreCase(logServer.getIp()) && server.getPort().equals(logServer.getPort())){
                        logServerInfo.getCurrentComputer().remove(logServer);
                        break;
                    }
                }
            }

            if (logServerInfo.getAll() != null){
                for(LogServer server:logServerInfo.getAll()){
                    if (server.getIp().equalsIgnoreCase(logServer.getIp()) && server.getPort().equals(logServer.getPort())){
                        logServerInfo.getAll().remove(logServer);
                        break;
                    }
                }
            }

        } finally {
            try {
                if (!socket.isClosed()) {
                    socket.close();
                }
            } catch (IOException e) {

            }
        }
    }

    private TTransport getTransport( LogServer logServer) {

        if (logServer != null ){
            TSocket socket = new TSocket(logServer.getIp(), logServer.getPort(), 2000);
            return new TFramedTransport(socket);
        }else{
            throw new RuntimeException("amp log not find data server");
        }

    }

}