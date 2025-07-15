package com.louis.coub.netty.withbilibili.shangguigu.nio.selector;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * @author John·Louis
 * @date create in 2019/12/22
 * description:
 */
public class NIOSelectorServer {

    public static void main(String[] args) throws IOException {
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
//        获取selector 对象
        Selector selector = Selector.open();
//        绑定一个端口
        serverSocketChannel.socket().bind(new InetSocketAddress(6666));

        serverSocketChannel.configureBlocking(false);

//        将serverSocketChannel注册到selector上面去， 关心的时间为OP_ACCEPT
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

        while (true) {
//            如果1秒钟内没有连接事件发生，那么就就直接返回
            if (selector.select(2000) == 0) {
                System.out.println("服务器等待了一秒，没有连接");
                continue;
            }
//            如果有值表示 已经获取到了关注的时间
//            通过selectionKeys反向获取通道
            Set<SelectionKey> selectionKeys = selector.selectedKeys();

            Iterator<SelectionKey> keyIterator = selectionKeys.iterator();

            while (keyIterator.hasNext()) {
                //                表示是一个连接的事件
                SelectionKey selectionKey = keyIterator.next();
                if (selectionKey.isAcceptable()) {
                    SocketChannel socketChannel = serverSocketChannel.accept();
//                      将当前的socketChannel注册到selector上面去
                    System.out.println("客户端连接成功，关联一个socketChannel" + socketChannel.toString());
                    socketChannel.configureBlocking(false);
                    socketChannel.register(selector, SelectionKey.OP_READ, ByteBuffer.allocate(1024));

                }
//                    是一个读取的事件
                if (selectionKey.isReadable()) {
//                        获取到读事件的channel

                    SocketChannel socketChannel = (SocketChannel) selectionKey.channel();
//                        获取到该channel中关联的buffer
                    ByteBuffer attachment = (ByteBuffer) selectionKey.attachment();
//                      todo 2019年12月22日15:49:48  这里当客户端断开连接的时候，服务端也会停止服务，需要优化
                    int read = socketChannel.read(attachment);
                    System.out.println("from 客户端： " + new String(attachment.array()));


                }
                keyIterator.remove();
            }

        }

    }
}
