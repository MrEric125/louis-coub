//package com.sf.oss;
//
//import lombok.extern.slf4j.Slf4j;
//import org.apache.commons.collections4.CollectionUtils;
//import org.apache.commons.lang3.StringUtils;
//import org.apache.http.client.methods.CloseableHttpResponse;
//import org.apache.http.client.methods.HttpPut;
//import org.apache.http.client.methods.HttpRequestBase;
//import org.apache.http.entity.AbstractHttpEntity;
//import org.apache.http.entity.FileEntity;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//import swiftsdk.SfOssClient;
//import swiftsdk.SwiftConfiguration;
//import swiftsdk.errors.SfOssException;
//import swiftsdk.http.HttpClientFactory;
//import swiftsdk.util.TokenCache;
//
//import java.io.File;
//import java.io.IOException;
//import java.util.List;
//
///**
// * @author louis
// * <p>
// * Date: 2019/8/14
// * Description:
// */
//@Slf4j
//@Component
//public class SwiftExtender {
//
//
//
//    @Autowired
//    private SwiftConfiguration swiftConfig;
//
//    @Autowired
//    private TokenCache tokenCache;
//
//    @Autowired
//    private SfOssClient sfOssClient;
//
//    /**
//     * 重命名obj
//     * @param fromObjName
//     * @param destObjName
//     * @return
//     */
//    public boolean renameObj(String container,String fromObjName, String destObjName) {
//        boolean flag = copyObj(container, container, fromObjName, destObjName);
//        if (flag) {
//            try {
//                flag=sfOssClient.deleteObject(container, fromObjName);
//
//
//                return flag;
//            } catch (SfOssException e) {
//                e.printStackTrace();
//            }
//        }
//
//        return flag;
//
//    }
//
//    /**
//     * 删除某个容器下带有相同前缀的obj
//     *
//     * @param container
//     * @param prefix
//     */
//    public Boolean deleteObj(String container, String prefix) {
//
//        boolean flag=false;
//        try {
//            List<String> fileNameList = sfOssClient.getObjectList(container, null, 1000, prefix, null, null);
//            if (CollectionUtils.isNotEmpty(fileNameList)) {
//                for (String x : fileNameList) {
//                    flag = sfOssClient.deleteObject(container, x);
//                }
//            }
//
//        } catch (SfOssException e) {
//            flag = false;
//            e.printStackTrace();
//            log.warn("DeleteLogJob：删除日志文件失败", e);
//        }
//        return flag;
//
//    }
//
//    public boolean uploadTar(String container, File file,String filename) throws SfOssException {
////        sfOssClient.uploadTar()
//        String url = this.swiftConfig.getSfossServerUrl() + "/v1/AUTH_" + this.swiftConfig.getAccount() + "/" + container;
//        return doUpload(url, new FileEntity(file));
//
//    }
//
//    public String getObjectPath(String container, String objectName) {
//        String path = null;
//        try {
//            List<String> objectList = sfOssClient.getObjectList(container, objectName, 1000);
//            if (!CollectionUtils.isEmpty(objectList)) {
//                String name = objectList.get(0);
//                path=swiftConfig.getSfossServerUrl() + "/v1/AUTH_" + swiftConfig.getAccount() + "/" + container + "/" + name;
//            }
//
//
//        } catch (SfOssException e) {
//            e.printStackTrace();
//        }
//        return path;
//
//    }
//
//
//    /**
//     *
//     * @param fromContainer 开始容器
//     * @param destContainer 终点容器
//     * @param fromObjName 开始对象名
//     * @param destObjName 结束对象名
//     * @return boolean
//     */
//    public boolean copyObj(String fromContainer,String destContainer,String fromObjName,String destObjName) {
//        String url = swiftConfig.getSfossServerUrl() + "/v1/AUTH_" + swiftConfig.getAccount() + "/" + destContainer + "/" + destObjName;
//        HttpPut httpPut = new HttpPut(url);
//        httpPut.setHeader("X-Copy-From", fromContainer + "/" + fromObjName);
//        httpPut.setHeader("X-Auth-Token", tokenCache.getToken());
//        CloseableHttpResponse response ;
//
//        try {
//            List<String> containerList = sfOssClient.getContainerList(null, 1000);
//            //判断destContainer是否存在
//            if (!containerList.contains(destContainer)) {
//                sfOssClient.createContainer(destContainer);
//            }
//            List<String> objectList = sfOssClient.getObjectList(fromContainer, null, 1000, fromObjName, "", "");
//            if (CollectionUtils.isEmpty(objectList)) {
//                return false;
//            }
//            response = HttpClientFactory.getHttpClient().execute(httpPut);
//            int statusCode = response.getStatusLine().getStatusCode();
//            if (statusCode >= 200 && statusCode < 300) {
//                httpPut.releaseConnection();
//                return true;
//            }
//        } catch (IOException e) {
//            httpPut.releaseConnection();
//            e.printStackTrace();
//        } catch (SfOssException e) {
//            e.printStackTrace();
//        }
//        httpPut.releaseConnection();
//        return false;
//    }
//
//
//    private boolean doUpload(String url, AbstractHttpEntity entity) throws SfOssException {
//        try {
//            HttpPut put = new HttpPut(url);
//            put.setEntity(entity);
//
//            try {
//                CloseableHttpResponse response;
//                response = this.doHttp(put);
//
//                int statusCode = response.getStatusLine().getStatusCode();
//                if (statusCode != 200 && statusCode != 202 && statusCode != 201) {
//                    if (statusCode == 404) {
//                        put.releaseConnection();
//                        throw new SfOssException("Resource does not exist");
//                    }
//                    return false;
//                } else {
//                    put.releaseConnection();
//                    return true;
//                }
//            } catch (Exception e) {
//                put.releaseConnection();
//                throw e;
//            }
//        } catch (SfOssException e) {
//            throw e;
//        } catch (Exception e) {
//            throw new SfOssException(e);
//        }
//
//    }
//
//    private CloseableHttpResponse doHttp(HttpRequestBase requestBase) throws Exception {
//        String token = tokenCache.getToken();
//        CloseableHttpResponse response;
//        if (token == null) {
//            throw new SfOssException("无法登录Sfoss");
//        }
//        else {
//            requestBase.setHeader("X-Auth-Token", token);
//            if (this.swiftConfig.getSecure()) {
//                response = HttpClientFactory.getHttpsClient().execute(requestBase);
//            } else {
//                response = HttpClientFactory.getHttpsClient().execute(requestBase);
//            }
//            int statusCode = response.getStatusLine().getStatusCode();
//            if (statusCode >= 300) {
//                throw new SfOssException("http 操作异常");
//            }
//        }
//        return response;
//
//    }
//
//    public boolean deleteContainersWithFile(String containerName) {
//        try {
//            return sfOssClient.deleteContainer(containerName, true);
//        } catch (SfOssException e) {
//            e.printStackTrace();
//        }
//        return false;
//    }
//
//
//
//    /**
//     * 上传zip 文件
//     * @param container
//     * @param filePath
//     * @return
//     */
//    public boolean uploadZip(String container, String filePath)  throws SfOssException{
//        if (StringUtils.isNotEmpty(container)) {
//            String url = swiftConfig.getSfossServerUrl() + "/v1/AUTH_" + swiftConfig.getAccount() + "/" + container;
//            String lower = filePath.toLowerCase();
//            if (lower.endsWith("tar")) {
//                url = url + "?extract-archive=tar";
//            } else {
//                throw new SfOssException("压缩格式不准确");
//            }
//
//            HttpPut httpPut = new HttpPut(url);
//            httpPut.setEntity(new FileEntity(new File(filePath)));
//            httpPut.setHeader("X-Auth-Token", tokenCache.getToken());
//            CloseableHttpResponse response = null;
//            try {
//                response = HttpClientFactory.getHttpClient().execute(httpPut);
//                int statusCode = response.getStatusLine().getStatusCode();
//                if (statusCode >= 200 && statusCode < 300) {
//                    httpPut.releaseConnection();
//                    return true;
//                }
//            } catch (IOException e) {
//                httpPut.releaseConnection();
//                e.printStackTrace();
//            }
//            httpPut.releaseConnection();
//
//        }
//
//        return false;
//    }
//
//}
