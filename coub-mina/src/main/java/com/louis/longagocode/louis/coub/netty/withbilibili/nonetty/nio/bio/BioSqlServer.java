package com.louis.longagocode.louis.coub.netty.withbilibili.nonetty.nio.bio;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author louis
 * <p>
 * Date: 2019/11/4
 * Description:
 */
public class BioSqlServer {
    static byte[] bs = new byte[1024];

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket();
        while (true) {
            Socket accept = serverSocket.accept();
            accept.getInputStream().read(bs);
            System.out.println(new String(bs));

        }
    }
}
