package com.louis.kafka.common;


import java.util.Properties;

/**
 * 客户端 producer,consumer等的公用属性
 */
public abstract class ClientTemplate {

    protected Properties properties = new Properties();

    protected AuthInfo authInfo;

    protected ClusterInfo clusterInfo;

    public abstract void doInit() throws Exception;


    public AuthInfo getAuthInfo() {
        return authInfo;
    }

    public void setAuthInfo(AuthInfo authInfo) {
        this.authInfo = authInfo;
    }
    public Properties getProperties() {
        return this.properties;
    }


    public ClusterInfo getClusterInfo() {
        return clusterInfo;
    }

    public void setClusterInfo(ClusterInfo clusterInfo) {
        this.clusterInfo = clusterInfo;
    }
}
