package com.netty.withbilibili.nonetty;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

public class ClientHandler{

    public static final int Max_DATA_LEN=1024;
    private final Socket socket;
    public ClientHandler(Socket socket){
        this.socket=socket;
    }
    public void start(){
        new Thread(this::doStart).start();
    }
    private void doStart(){
        try {
            InputStream inputStream = socket.getInputStream();
            while (true) {
                byte[] bytes = new byte[Max_DATA_LEN];
                int len;
                while ((len = inputStream.read(bytes)) != -1) {
                    String message = new String(bytes, 0, len);
                    System.out.println("客户端传来的消息：" + message);
                    socket.getOutputStream().write(bytes);

                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}