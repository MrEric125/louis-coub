package com.louis.objectcontainer;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.FileEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import swiftsdk.SfOssClient;
import swiftsdk.SwiftConfiguration;
import swiftsdk.errors.SfOssException;
import swiftsdk.http.HttpClientFactory;
import swiftsdk.util.TokenCache;

import java.io.File;
import java.io.IOException;

/**
 * @author louis
 * <p>
 * Date: 2019/8/14
 * Description:
 */

@Component
public class SwiftExtender {



    @Autowired
    private SwiftConfiguration swiftConfig;

    @Autowired
    private TokenCache tokenCache;

    @Autowired
    private SfOssClient sfOssClient;

    /**
     * 重命名obj
     * @param fromObjName
     * @param destObjName
     * @return
     */
    public boolean renameObj(String container,String fromObjName, String destObjName) {
        boolean flag = copyObj(container, container, fromObjName, destObjName);
        if (flag) {
            try {
                flag=sfOssClient.deleteObject(container, fromObjName);

                // TODO: 2019/8/15 输出日志
                return flag;
            } catch (SfOssException e) {
                e.printStackTrace();
            }
        }
        // TODO: 2019/8/15  输出日志
        return flag;



    }


    /**
     *
     * @param fromContainer 开始容器
     * @param destContainer 终点容器
     * @param fromObjName 开始对象名
     * @param destObjName 结束对象名
     * @return boolean
     */
    public boolean copyObj(String fromContainer,String destContainer,String fromObjName,String destObjName) {
        String url = swiftConfig.getSfossServerUrl() + "/v1/AUTH_" + swiftConfig.getAccount() + "/" + destContainer + "/" + destObjName;
        HttpPut httpPut = new HttpPut(url);
        httpPut.setHeader("X-Copy-From", fromContainer + "/" + fromObjName);
        httpPut.setHeader("X-Auth-Token", tokenCache.getToken());
        CloseableHttpResponse response ;

        try {
            response = HttpClientFactory.getHttpClient().execute(httpPut);
            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode >= 200 && statusCode < 300) {
                httpPut.releaseConnection();
                return true;
            }
        } catch (IOException e) {
            httpPut.releaseConnection();
            e.printStackTrace();
        }
        httpPut.releaseConnection();
        return false;
    }

    /**
     * 上传zip 文件
     * @param container
     * @param filePath
     * @return
     */
    public boolean uploadZip(String container, String filePath)  throws SfOssException{
        if (StringUtils.isNotEmpty(container)) {
            String url = swiftConfig.getSfossServerUrl() + "/v1/AUTH_" + swiftConfig.getAccount() + "/" + container;
            String lower = filePath.toLowerCase();
            if (lower.endsWith("tar")) {
                url = url + "?extract-archive=tar";
            } else {
                throw new SfOssException("压缩格式不准确");
            }

            HttpPut httpPut = new HttpPut(url);
            httpPut.setEntity(new FileEntity(new File(filePath)));
            httpPut.setHeader("X-Auth-Token", tokenCache.getToken());
            CloseableHttpResponse response = null;
            try {
                response = HttpClientFactory.getHttpClient().execute(httpPut);
                int statusCode = response.getStatusLine().getStatusCode();
                if (statusCode >= 200 && statusCode < 300) {
                    httpPut.releaseConnection();
                    return true;
                }
            } catch (IOException e) {
                httpPut.releaseConnection();
                e.printStackTrace();
            }
            httpPut.releaseConnection();

        }

        return false;
    }

}
