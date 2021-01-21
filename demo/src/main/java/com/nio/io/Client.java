package com.nio.io;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;

/**
 * @author Louis
 * @date created on 2021/1/20
 * description:
 * todo 客户端和服务端总共有多少个socket,总共会有多少个地方阻塞
 *
 */
public class Client {

    public static void main(String[] args) {
        try {
            Socket socket = new Socket("127.0.0.1", 1234);
            //连完之后可以不发送数据的
            socket.getOutputStream().write("121212".getBytes());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
