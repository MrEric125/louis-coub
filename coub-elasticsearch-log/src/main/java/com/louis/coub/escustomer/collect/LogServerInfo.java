package com.louis.coub.escustomer.collect;

import java.util.List;

public class LogServerInfo {
    public final static int UPLOAD_MODE_CURRENT = 1;

    public final static int UPLOAD_MODE_RANDOM = 2;

    public final static int UPLOAD_MODE_MIND = 3;

    private List<LogServer> currentComputer;

    private List<LogServer> all;
    /**
     * 数据上报模式,默认为本机房优先
     * 1=强制本机房上报,可能会出现机房可服务实例少的情况下，压死服务，然后数据丢失,同时报警吧
     * 2=随机上报
     * 3=智能上报,本机房服务实例大于等于serviceCount，就采用本机房上报，否则采用随机上报
     */
    private int uploadMode = 2;
    /**
     * 本机房可服务实例大于等于多少实例数量时，采用本机房上报
     */
    private int serviceCount = 5;

    public int getUploadMode() {
        return uploadMode;
    }

    public void setUploadMode(int uploadMode) {
        this.uploadMode = uploadMode;
    }

    public int getServiceCount() {
        if (serviceCount < 3){
            this.serviceCount = 3;
        }
        return serviceCount;
    }

    public void setServiceCount(int serviceCount) {
        this.serviceCount = serviceCount;
    }

    public List<LogServer> getCurrentComputer() {
        return currentComputer;
    }

    public void setCurrentComputer(List<LogServer> currentComputer) {
        this.currentComputer = currentComputer;
    }

    public List<LogServer> getAll() {
        return all;
    }

    public void setAll(List<LogServer> all) {
        this.all = all;
    }
}
