package com.louis.nio;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

/**
 * @author louis
 * <p>
 * Date: 2019/7/5
 * Description:
 */
public class NIODemo {

    public static void main(String[] args) {
        try {
            int port = 8080;
            ServerSocketChannel socketChannel = ServerSocketChannel.open();
            socketChannel.socket().bind(new InetSocketAddress(InetAddress.getByName("IP"),port));
            socketChannel.configureBlocking(false);


            Selector selector = Selector.open();


            SocketChannel channel = socketChannel.accept();
            channel.configureBlocking(false);
            channel.socket().setReuseAddress(true);

            socketChannel.register(selector, SelectionKey.OP_READ);


        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
