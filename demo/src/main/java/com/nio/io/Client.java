package com.nio.io;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Scanner;

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
            Socket socket = new Socket("localhost", 8080);
            //连完之后可以不发送数据的
//            socket.getOutputStream().write("121212".getBytes());


            while (true) {
                Scanner scanner = new Scanner(System.in);

                String sss = scanner.nextLine();

                socket.getOutputStream().write(sss.getBytes());
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
