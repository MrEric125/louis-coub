package com.nio.channel;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.StandardCharsets;

/**
 * @author louis
 * <p>
 * Date: 2019/12/9
 * Description:
 * 文件通道总是阻塞式的，不能被设置为异步模式
 *
 * 可能会造成文件空洞的问题
 *
 */
public class FileChannelFileHole {
    private static void putData(int positiopn, ByteBuffer byteBuffer, FileChannel fileChannel) throws IOException {
        String string = "*<-- location " + positiopn;
        byteBuffer.clear();
        byteBuffer.put(string.getBytes(StandardCharsets.UTF_8));
        byteBuffer.flip();
        fileChannel.position(positiopn);
        fileChannel.write(byteBuffer);
    }

    public static void main(String[] args) throws IOException {
        File temp = File.createTempFile("holy", null);
        RandomAccessFile randomAccessFile = new RandomAccessFile(temp, "rw");
        FileChannel fileChannel = randomAccessFile.getChannel();
        ByteBuffer buffer = ByteBuffer.allocateDirect(100);
        putData(0, buffer, fileChannel);
        putData(5000000, buffer, fileChannel);
        putData(50000, buffer, fileChannel);
        System.out.println("wrote temp file '" + temp.getPath() + "',size=" + fileChannel.size());
        fileChannel.close();

        randomAccessFile.close();

    }
}
