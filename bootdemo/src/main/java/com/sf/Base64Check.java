package com.sf;

import org.apache.commons.lang3.StringUtils;

/**
 * @author louis
 * <p>
 * Date: 2019/8/26
 * Description:
 */
public class Base64Check {


    public static void main(String[] args) {
        String tmpBasePath = "/nfsc/ELOG_FLUX_WMO/zip/zip/";
        String fileTmpDir = "/nfsc/ELOG_FLUX_WMO/zip/zip/2019-09-03/deviceid/sss/1/2019-09-03_uploadTest2.txt";
        String basePath = "/nfsc/ELOG_FLUX_WMO/zip/real/";
        String replace = StringUtils.replace(fileTmpDir, tmpBasePath, basePath);
        System.out.println(replace);
    }

}
