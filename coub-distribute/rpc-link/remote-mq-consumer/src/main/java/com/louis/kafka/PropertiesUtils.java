package com.louis.kafka;

import java.util.Properties;

/**
 * @author jun.liu
 * @since 2021/6/21 15:42
 */
public class PropertiesUtils {
    public static void fillEmptyPropWithDefVal(Properties prop, String pName, String defVal) {
        Object originVal = prop.get(pName);
        String realVal = originVal == null ? defVal : ((String) originVal).trim();
        prop.put(pName, realVal);
    }
}
