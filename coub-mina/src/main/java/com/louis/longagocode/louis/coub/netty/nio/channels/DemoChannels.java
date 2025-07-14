package com.louis.longagocode.louis.coub.netty.nio.channels;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author Louis
 * @date created on 2021/1/19
 * description:
 */
public class DemoChannels {

    public static void main(String[] args) throws IOException {
        RandomAccessFile raf = new RandomAccessFile("D:\\workspace\\IntelliJ Idea\\source\\boot-mybatis\\demo\\src\\main\\java\\com\\nio\\channels\\data.txt", "rw");

        FileChannel inChannel = raf.getChannel();

        ByteBuffer buf = ByteBuffer.allocate(50);

        int byteRead = inChannel.read(buf);

        ByteBuffer buf2 = ByteBuffer.allocate(50);

        buf2.put("filechannel test".getBytes());

        buf2.flip();

        inChannel.write(buf);

        while (byteRead != -1) {
            System.out.print("read " + byteRead);

            buf.flip();

            while (buf.hasRemaining()) {
                System.out.print((char) buf.get());

            }

            buf.clear();

            byteRead = inChannel.read(buf);
        }

        raf.close();
    }



}
