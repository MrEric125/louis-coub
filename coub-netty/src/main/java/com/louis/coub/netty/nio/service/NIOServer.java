package com.louis.coub.netty.nio.service;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author cyq
 * NIO通讯服务端
 */
public class NIOServer {

    private final int port = 8888;

    //解码buffer
    private final CharsetDecoder decode = StandardCharsets.UTF_8.newDecoder();
    /*发送数据缓冲区*/
    private final ByteBuffer sBuffer = ByteBuffer.allocate(1024);

    private final ByteBuffer direct = ByteBuffer.allocateDirect(1024);
    /*接受数据缓冲区*/
    private final ByteBuffer rBuffer = ByteBuffer.allocate(1024);
    /*映射客户端channel */
    private final Map<String, SocketChannel> clientsMap = new ConcurrentHashMap<>();
    private Selector selector;
    private final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", java.util.Locale.US);

    public NIOServer() {
        try {
            init();
            listen();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void init() throws IOException {
        /*
         *启动服务器端，配置为非阻塞，绑定端口，注册accept事件
         *ACCEPT事件：当服务端收到客户端连接请求时，触发该事件
         */
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.configureBlocking(false);
        ServerSocket serverSocket = serverSocketChannel.socket();
        serverSocket.bind(new InetSocketAddress(port));
        selector = Selector.open();
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        System.out.println("server start on port:" + port);
    }

    /**
     * 服务器端轮询监听，select方法会一直阻塞直到有相关事件发生或超时
     */
    private void listen() {

        while (true) {
            try {
                selector.select();//返回值为本次触发的事件数
                Set<SelectionKey> selectionKeys = selector.selectedKeys();
                Iterator<SelectionKey> iterator = selectionKeys.iterator();

                if (iterator.hasNext()) {
                    SelectionKey key = iterator.next();
                    handle(key);
                    iterator.remove();

                }
            } catch (Exception e) {
                e.printStackTrace();
                break;
            }
        }
    }

    /**
     * 处理不同的事件
     */
    private void handle(SelectionKey selectionKey) throws IOException {

        ServerSocketChannel server = null;
        SocketChannel client = null;
        String receiveText = null;
        int count = 0;
        if (selectionKey.isAcceptable()) {
            /*
             * 客户端请求连接事件
             * serversocket为该客户端建立socket连接，将此socket注册READ事件，监听客户端输入
             * READ事件：当客户端发来数据，并已被服务器控制线程正确读取时，触发该事件
             */
            System.out.println("key type :ACCEPTABLE");
            server = (ServerSocketChannel) selectionKey.channel();
            client = server.accept();
            client.configureBlocking(false);
            client.register(selector, SelectionKey.OP_READ);
            //todo 嫁入当前客户端是断开链接的，那么这里还是会一直进入到这里
        } else if (selectionKey.isReadable()) {
            /*
             * READ事件，收到客户端发送数据，读取数据后继续注册监听客户端
             */
            client = (SocketChannel) selectionKey.channel();
            rBuffer.clear();
            count = client.read(rBuffer);
            System.out.println("key type :READABLE");
            if (count > 0) {
                rBuffer.flip();
                receiveText = decode.decode(rBuffer.asReadOnlyBuffer()).toString();
                System.out.println(client.toString() + ":" + receiveText);
                sBuffer.clear();
                sBuffer.put((sdf.format(new Date()) + "服务器收到你的消息").getBytes());
                sBuffer.flip();
                client.write(sBuffer);
                dispatch(client, receiveText);
                client = (SocketChannel) selectionKey.channel();
                client.register(selector, SelectionKey.OP_READ);
            }
        }
    }

    /**
     * 把当前客户端信息 推送到其他客户端
     */
    private void dispatch(SocketChannel client, String info) throws IOException {

        Socket s = client.socket();
        String name = "[" + s.getInetAddress().toString().substring(1) + ":" + Integer.toHexString(client.hashCode()) + "]";
        if (!clientsMap.isEmpty()) {
            for (Map.Entry<String, SocketChannel> entry : clientsMap.entrySet()) {
                SocketChannel temp = entry.getValue();
                if (!client.equals(temp)) {
                    sBuffer.clear();
                    sBuffer.put((name + ":" + info).getBytes());
                    sBuffer.flip();
                    //输出到通道
                    temp.write(sBuffer);
                }
            }
        }
        clientsMap.put(name, client);
    }

    public static void main(String[] args) throws IOException {

        new NIOServer();
    }
}

