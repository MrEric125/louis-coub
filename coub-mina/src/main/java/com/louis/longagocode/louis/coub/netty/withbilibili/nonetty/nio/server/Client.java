package com.louis.longagocode.louis.coub.netty.withbilibili.nonetty.nio.server;

import java.io.IOException;
import java.net.Socket;

/**
 * @author John·Louis
 * @date create in 2019/11/28
 * description:
 */
public class Client {


    private static final String HOST = "127.0.0.1";

    private static final int PORT = 8000;

    private static final int SLEEP_TIME = 500;

    public static void main(String[] args) throws IOException {
        final Socket socket = new Socket(HOST, PORT);
        new Thread(() -> {
            System.out.println("客户端启动成功");
            while (true) {
                try {
                    String message = "hello world";
                    System.out.println("客户端发送消息为" + message);
                    socket.getOutputStream().write(message.getBytes());

                } catch (IOException e) {
                    e.printStackTrace();
                }
                sleep();
            }
        });
    }

    private static void sleep() {
        try {
            Thread.sleep(SLEEP_TIME);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

}
