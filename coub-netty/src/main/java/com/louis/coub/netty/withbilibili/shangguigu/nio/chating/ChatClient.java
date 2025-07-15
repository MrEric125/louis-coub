package com.louis.coub.netty.withbilibili.shangguigu.nio.chating;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author John·Louis
 * @date create in 2019/12/22
 * description:
 */
public class ChatClient {

    public static final String HOST = "localhost";

    public static final int PORT = 6667;

    private Selector selector;

    private SocketChannel socketChannel;

    private String userName;

    public ChatClient() throws IOException {
        selector = Selector.open();

        socketChannel = SocketChannel.open(new InetSocketAddress(HOST, PORT));
        socketChannel.configureBlocking(false);

        socketChannel.register(selector, SelectionKey.OP_READ);


        userName = socketChannel.getLocalAddress().toString().substring(1);

        System.out.println(userName + " is ok");

    }

    public static void main(String[] args) throws IOException {
        ChatClient client = new ChatClient();

        ExecutorService service = Executors.newCachedThreadPool();
//        读取从服务端发送过来的数据
        service.execute(() -> {
            while (true) {
                client.readInfo();
                try {
                    Thread.sleep(3000);
                    System.out.println("等待接收数据。。。");

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        });
//        发送数据

        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            client.sendInfo(line);

        }

    }

    public void sendInfo(String info) {

        info = userName + " 说： " + info;

        try {
            socketChannel.write(ByteBuffer.wrap(info.getBytes()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //    获取回复消息
    public void readInfo() {
        try {
            int select = selector.select();
//            有事件发生
            if (select > 0) {
                Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
                while (iterator.hasNext()) {
                    SelectionKey key = iterator.next();
                    if (key.isReadable()) {
                        SocketChannel channel = (SocketChannel) key.channel();
                        ByteBuffer buffer = ByteBuffer.allocate(1024);
                        channel.read(buffer);
                        String msg = new String(buffer.array());
                        System.out.println(msg.trim());
                    }
                    iterator.remove();

                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
