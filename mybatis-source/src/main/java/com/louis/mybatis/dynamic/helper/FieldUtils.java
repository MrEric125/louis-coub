package com.louis.mybatis.dynamic.helper;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author louis
 * <p>
 * Date: 2019/9/19
 * Description:
 */
public class FieldUtils {


    private static Pattern humpPattern = Pattern.compile("[A-Z]");
    /** 驼峰转下划线,效率比上面高 */
    public static String humpToLine(String str) {
        Matcher matcher = humpPattern.matcher(str);
        StringBuffer sb = new StringBuffer();
        while (matcher.find()) {
            matcher.appendReplacement(sb, "_" + matcher.group(0).toLowerCase());
        }
        matcher.appendTail(sb);
        String returnStr = sb.toString();
        if (returnStr.startsWith("_")) {
            returnStr = returnStr.substring(1);
        }
        return returnStr;
    }
}
