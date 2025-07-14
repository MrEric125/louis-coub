package com.louis;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;

public class NioServer {

    public static void main(String[] args) throws IOException {
        // 创建服务端SocketChannel
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.socket().bind(new InetSocketAddress(8080));
        serverSocketChannel.configureBlocking(false);

        System.out.println("Server started. Listening for incoming connections...");

        while (true) {
            SocketChannel socketChannel = serverSocketChannel.accept();
            if (socketChannel != null) {
                System.out.println("Client connected: " + socketChannel.getRemoteAddress());

                // 处理客户端请求
                handleClientRequest(socketChannel);
            }
        }
    }

    private static void handleClientRequest(SocketChannel socketChannel) throws IOException {
        ByteBuffer buffer = ByteBuffer.allocate(1024);

        // 读取客户端发送的数据
        int bytesRead = socketChannel.read(buffer);
        if (bytesRead > 0) {
            buffer.flip();
            byte[] bytes = new byte[buffer.remaining()];
            buffer.get(bytes);
            String request = new String(bytes, StandardCharsets.UTF_8);
            System.out.println("Received request from client: " + request);

            // 处理请求并发送响应给客户端
            String response = "Hello from server!";
            ByteBuffer responseBuffer = ByteBuffer.wrap(response.getBytes(StandardCharsets.UTF_8));
            socketChannel.write(responseBuffer);
        }

        socketChannel.close();
    }
}
