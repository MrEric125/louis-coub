package com.louis.longagocode.louis.coub.netty.withbilibili.nonetty.nio.server;


import java.io.IOException;

public class ServerBoot{

    private static final int PORT=8000;

    public static void main(String[] args) throws IOException {
        Server server = new Server(PORT);
        server.start();
    }
}