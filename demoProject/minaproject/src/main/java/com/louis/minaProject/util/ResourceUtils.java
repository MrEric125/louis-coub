package com.louis.minaProject.util;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * @author jun.liu
 * @date created on 2020/5/21
 * description:
 */
public class ResourceUtils {
    public static String getEnglishValueByKey(String key){

        Locale locale = new Locale("en", "US");
        //使用指定的英文Locale
        ResourceBundle mySource = ResourceBundle.getBundle("i18n/message", locale);
        return mySource.getString(key);
    }

    public static String getChineseValueByKey(String key){

        Locale locale = new Locale("zh", "CN");
        //使用指定的中文Locale
        ResourceBundle mySource = ResourceBundle.getBundle("i18n/message", locale);
        return mySource.getString(key);
    }

    public static String getDeafultValueByKey(String key){

        //使用默认的Locale
        ResourceBundle mySource = ResourceBundle.getBundle("i18n/message");
        return mySource.getString(key);
    }

    public static String getValueAndPlaceholder(String key){

        //使用默认的Locale
        ResourceBundle mySource = ResourceBundle.getBundle("i18n/message");

        String beforeValue = mySource.getString(key);

        //填充国家化文件中的占位符
        String afterValue = MessageFormat.format(beforeValue, "安全");
        return afterValue;
    }

    public static void main(String[] args) {

        String englishValueByKey = getEnglishValueByKey("com.website.operation");

    }
}
