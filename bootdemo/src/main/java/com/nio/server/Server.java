package com.nio.server;


import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server{

    private ServerSocket serverSocket;

    public Server(int port) throws IOException {
        this.serverSocket=new ServerSocket(port);
        System.out.println(("服务器启动成功，端口： " + port));

    }
    public void start(){
        new Thread(this::doStart);
    }
    private void doStart(){
        while(true){
            try {
                Socket server = serverSocket.accept();
                new ClientHandler(server).start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}