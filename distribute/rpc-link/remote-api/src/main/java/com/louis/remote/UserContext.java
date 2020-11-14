package com.louis.remote;

/**
 * @author jun.liu
 * @date created on 2020/7/12
 * description:
 */
public class UserContext {

    private static final ThreadLocal<String> USERNAME = new ThreadLocal<>();

    public static String getUsername() {

        return USERNAME.get();
    }
    public static void putUsername(String username) {
        USERNAME.set(username);

    }
}
