package com.louis;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;

public class NIOClient {
    public static void main(String[] args) throws IOException {
        // 创建客户端SocketChannel
        SocketChannel socketChannel = SocketChannel.open();
        socketChannel.configureBlocking(false);
        socketChannel.connect(new InetSocketAddress("localhost", 8080));

        while (!socketChannel.finishConnect()) {
            // 等待连接完成
        }

        System.out.println("Connected to server: " + socketChannel.getRemoteAddress());

        // 向服务端发送数据
        String message = "Hello from client!";
        ByteBuffer buffer = ByteBuffer.wrap(message.getBytes(StandardCharsets.UTF_8));
        socketChannel.write(buffer);

        // 接收服务端的响应
        ByteBuffer responseBuffer = ByteBuffer.allocate(1024);
        int bytesRead = socketChannel.read(responseBuffer);
        if (bytesRead > 0) {
            responseBuffer.flip();
            byte[] bytes = new byte[responseBuffer.remaining()];
            responseBuffer.get(bytes);
            String response = new String(bytes, StandardCharsets.UTF_8);
            System.out.println("Received response from server: " + response);
        }

        socketChannel.close();
    }
}