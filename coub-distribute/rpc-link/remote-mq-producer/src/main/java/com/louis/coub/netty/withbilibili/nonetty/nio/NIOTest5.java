package com.louis.coub.netty.withbilibili.nonetty.nio;

import org.junit.jupiter.api.Test;
import org.springframework.core.io.ClassPathResource;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.Channel;
import java.nio.channels.FileChannel;
import java.nio.channels.Selector;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

/**
 * @author louis
 * <p>
 * Date: 2019/10/22
 * Description:
 */
public class NIOTest5 {

    /**
     * 1. 读取文件获取FileChannel
     * 2. 给ByteBuffer 分配大小，并且得到ByteBuffer
     * 3. 将数据读取到Buffer中
     * @throws Exception
     */
    @Test
    public void test1() throws Exception {


//        这种方式获取的是编译之后的class上的文件
        ClassPathResource resource = new ClassPathResource("");
        resource.getFile();
        Path path = Paths.get("/");

        RandomAccessFile randomAccessFile = new RandomAccessFile("F:\\louis\\boot-mybatis\\rpc-link\\remote-producer\\src\\main\\resources\\input.txt","rw");

        FileChannel fileChannel = randomAccessFile.getChannel();

        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);

//        将数据读取到ByteBuffer上面
        int read = fileChannel.read(byteBuffer);
        while (read != -1) {
            System.out.println("read: " + read);
//            翻转
            byteBuffer.flip();
//            读取buffer中的数据
            while (byteBuffer.hasRemaining()) {
                System.out.print((char) byteBuffer.get());
            }
            System.out.println();
            byteBuffer.clear();
            read = fileChannel.read(byteBuffer);
        }
        randomAccessFile.close();
    }

    /**
     * select
     * 1. 为什么要使用Selector
     * 2. Selector的创建
     * 3. 向Selector注册通道
     * 4. selectionKey
     * 5. 通过Selector选择通道
     * 6. wakeUp()
     * 7. close()
     *
     */
    @Test
    public void selectorTest() throws IOException {

        FileInputStream fileInputStream = new FileInputStream("");
        Channel channel = fileInputStream.getChannel();
        Selector selector = Selector.open();
        ByteBuffer byteBuffer = ByteBuffer.allocate(121);
        Stream.of(byteBuffer).forEach(buffer -> buffer.get());
    }
}
