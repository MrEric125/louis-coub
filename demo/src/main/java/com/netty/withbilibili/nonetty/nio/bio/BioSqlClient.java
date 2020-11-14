package com.netty.withbilibili.nonetty.nio.bio;

import java.io.IOException;
import java.net.Socket;

/**
 * @author louis
 * <p>
 * Date: 2019/11/4
 * Description:
 */
public class BioSqlClient {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("", 8000);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
