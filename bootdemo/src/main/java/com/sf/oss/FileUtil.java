package com.sf.oss;

import org.apache.commons.lang3.StringUtils;

import java.util.UUID;

/**
 * @author louis
 * <p>
 * Date: 2019/8/14
 * Description:
 */
public class FileUtil {

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

    public static String uuID(){
        return StringUtils.replace(UUID.randomUUID().toString(), "-", "");
    }

}
