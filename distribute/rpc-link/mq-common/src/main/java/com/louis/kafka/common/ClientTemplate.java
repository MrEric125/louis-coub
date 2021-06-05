package com.louis.kafka.common;


import com.louis.kafka.common.AuthInfo;
import com.louis.kafka.common.ClusterInfo;

import javax.annotation.PostConstruct;
import java.util.Properties;

/**
 * 客户端 producer,consumer等的公用属性
 */
public abstract class ClientTemplate {

    protected Properties properties;

    protected AuthInfo authInfo;

    protected ClusterInfo clusterInfo;

    @PostConstruct
    public abstract void doInit() throws Exception;


    public AuthInfo getAuthInfo() {
        return authInfo;
    }

    public void setAuthInfo(AuthInfo authInfo) {
        this.authInfo = authInfo;
    }

    public ClusterInfo getClusterInfo() {
        return clusterInfo;
    }

    public void setClusterInfo(ClusterInfo clusterInfo) {
        this.clusterInfo = clusterInfo;
    }
}
