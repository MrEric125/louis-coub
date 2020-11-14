package com.sf.oss;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;

import java.util.Date;
import java.util.UUID;

/**
 * @author louis
 * <p>
 * Date: 2019/8/14
 * Description:
 */
public class FileUtil {

    public FileUtil(String fileName) {
        System.out.println(fileName);
    }

    /**
     * 将文件名用uuid替换
     * @param fileName
     * @return
     */
    public static String convertFileName(String fileName){
        if(fileName==null || fileName.lastIndexOf('.')<0 ){
            return fileName;
        }
        return uuID()+"."+fileName.substring(fileName.lastIndexOf('.') + 1);
    }

    /**
     * 将包含container的object前缀截取
     * @param containerName
     * @return
     */
    public static String splitContainer(String path,String containerName) {
        String objName = path;
        if (path.startsWith("/")) {
            objName = objName.substring(1);
            return splitContainer(objName, containerName);
        }
        objName = StringUtils.replace(objName, containerName + "/", "", 1);
        return objName;

    }


    public static String uuID(){
        return StringUtils.replace(UUID.randomUUID().toString(), "-", "");
    }

    public static void main(String[] args) {
        String basePath = "/nfsc/ELOG_FLUX_WMO/issue/target/";
//        Date beforeDate = DateUtils.addWeeks(new Date(), -1);
        Date beforeDate = new Date();
        String beforeDateStr = DateFormatUtils.format(beforeDate, DateFormatUtils.ISO_DATE_FORMAT.getPattern());

        String prefixBasePath = FileUtil.splitContainer(basePath, "nfsc");
        System.out.println(beforeDateStr);
        System.out.println(prefixBasePath);
    }

}
