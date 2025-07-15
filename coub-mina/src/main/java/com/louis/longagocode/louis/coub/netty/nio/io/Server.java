package com.louis.longagocode.louis.coub.netty.nio.io;


import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author Louis
 * @date created on 2021/1/20
 * description:
 * 典型的bio
 */
public class Server {

    private static byte[] bytes = new byte[1024];

    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket();
            serverSocket.bind(new InetSocketAddress(8080));


            System.out.println("wait conn");
            Socket socket = serverSocket.accept();

            while (true) {

                //阻塞,当时可能会放弃cpu资源

                System.out.println("wait read");

                //read 的时候也会阻塞 ，读到的东西放到bytes中
                int read = socket.getInputStream().read(bytes);

                System.out.println("read success");

                String string = new String(bytes);

                System.out.println(string);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }


}
