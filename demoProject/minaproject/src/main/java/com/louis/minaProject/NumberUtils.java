package com.louis.minaProject;

import java.util.regex.Pattern;

/**
 * @author jun.liu
 * @date created on 2020/7/5
 * description:
 */
public class NumberUtils {

    public static Boolean isInteger(String str) {
        Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");
        return pattern.matcher(str).matches();

    }
}
