/*
package com.louis.swift;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.junit.Before;
import org.junit.Test;
import swiftsdk.SfOssClient;
import swiftsdk.SwiftConfiguration;
import swiftsdk.errors.SfOssException;

import java.util.List;

*/
/**
 * @author louis
 * <p>
 * Date: 2019/8/12
 * Description:
 *//*

@Slf4j
public class SwiftTest {


    private SwiftConfiguration configuration=new SwiftConfiguration();

    private SfOssClient sfOssClient;

    @Before
    public void before() {
        configuration.setUserName("ELOG-FLUX-WMO-SIT");
        configuration.setAccount("ELOG-FLUX-WMO-SIT");
        configuration.setUserKey("ef29f261491cf1d47f10e719c3537728");
        configuration.setSfossServerUrl("http://ELOG-FLUX-WMO-DCN.osssit.sfdc.com.cn:8080");
        sfOssClient = new SfOssClient(configuration);
    }


    @Test
    public void test1() {
        try {
//            Map<String, String> map = sfOssClient.headAontainerMeta();
//            Map<String, String> map = sfOssClient.he
//            log.info("response message.{}", b);
//            boolean container = sfOssClient.createContainer("cacaca/cacca");
//            List<String> containerList = sfOssClient.getContainerList(null, 100);
//            log.info("containerList:{}", containerList);

            long beganTime = System.currentTimeMillis();
            boolean b = sfOssClient.uploadFile("aaa", "test1/test2/test3", "E:\\patch.jar");

//            List<String> aaa = sfOssClient.getObjectList("aaa", null, 1000);
//            aaa.forEach(System.out::println);
            long endTime = System.currentTimeMillis();
            System.out.println(endTime - beganTime);

        } catch (SfOssException e) {
            e.printStackTrace();
        }
    }
    @Test
    public void test2() {
        String tempBasePath = "/nfsc/ELOG_FLUX_WMO/zip/zip/";
        String basePath = "/nfsc/ELOG_FLUX_WMO/zip/real/";
        String fileTempDir = "/nfsc/ELOG_FLUX_WMO/zip/zip/zhangsan.zip";
        String replace = StringUtils.replace(fileTempDir, tempBasePath, null);
        System.out.println(replace);

    }
}
*/
