package com.louis.coub.netty.nio.socket;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;

/**
 * @author louis
 * <p>
 * Date: 2019/12/11
 * Description:
 */
public class SelectSockets {


    public static int PORT = 9876;


    private ByteBuffer buffer = ByteBuffer.allocateDirect(1024);


    public static void main(String[] args) throws IOException {
        new SelectSockets().go(args);
    }
    public void go(String []argv) throws IOException {
        int port = PORT;
        if (argv.length > 0) {
            // Override default listen port
           port = Integer.parseInt(argv[0]);
             }
            System.out.println("Listening on port " + port);
        ServerSocketChannel socketChannel = ServerSocketChannel.open();
        ServerSocket serverSocket = socketChannel.socket();
        Selector selector = Selector.open();
        serverSocket.bind(new InetSocketAddress(port));

        socketChannel.configureBlocking(false);

        socketChannel.register(selector, SelectionKey.OP_ACCEPT);

        while (true) {
            int select = selector.select();
            if (select==0) {
                continue;
            }
            Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();

            while (iterator.hasNext()) {
                SelectionKey key = iterator.next();
                if (key.isAcceptable()) {
                    ServerSocketChannel server = (ServerSocketChannel) key.channel();
                    SocketChannel channel = server.accept();
                    registerChannel(selector, channel, SelectionKey.OP_READ);
                    sayHello(channel);

                }

                if (key.isReadable()) {
                    readDataFromSocket(key);
                }

                iterator.remove();

            }
        }
    }

    private void readDataFromSocket(SelectionKey key) throws IOException {
        SocketChannel socketChannel = (SocketChannel) key.channel();

        int count;
        buffer.clear();

        while ((count = socketChannel.read(buffer)) > 0) {

            while (buffer.hasRemaining()) {
                socketChannel.write(buffer);
            }
            buffer.clear();
        }
        if (count < 0) {
            socketChannel.close();

        }



    }

    private void sayHello(SocketChannel channel) throws IOException {
        buffer.clear();
        buffer.put("Hi there!\r\n".getBytes());
        buffer.flip();
        channel.write(buffer);
    }

    private void registerChannel(Selector selector, SocketChannel channel, int opRead) throws IOException {
        if (channel==null) {
            return;
        }
        channel.configureBlocking(false);
        channel.register(selector, opRead);

    }
}
