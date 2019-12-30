package com.netty.withbilibili.shangguigu.bio;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author John·Louis
 * @date create in 2019/12/19
 * description:
 * 如果没有客户端可以通过telnet来连接
 *
 */
@Slf4j
public class BIOServer {

    public static void main(String[] args) throws IOException {
        ExecutorService executorService = Executors.newCachedThreadPool();

        ServerSocket serverSocket = new ServerSocket(9999);
        log.info("服务器启动了");
        while (true) {
//            每个客户端的连接都会创建一个新的线程 如果没有线程连接，就会阻塞
            log.info("等待连接。。。。");
            Socket accept = serverSocket.accept();
            log.info("链接到一个客户端了");

            executorService.execute(()-> handler(accept));
        }
    }

    public static void handler(Socket socket) {
        byte[] data = new byte[1024];
        try {
//            获取read的时候也是会阻塞的
            // TODO: 2019/12/19  为什么这个地方会阻塞
            log.info("等待输入。。。。");
            InputStream inputStream = socket.getInputStream();
            while (true) {
                int read = inputStream.read(data);
                if (read == -1) {
                    break;
                }
                log.info(new String(data, 0, read));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }


    }
}
