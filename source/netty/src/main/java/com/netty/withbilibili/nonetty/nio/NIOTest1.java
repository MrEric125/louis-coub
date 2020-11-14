package com.netty.withbilibili.nonetty.nio;

import org.junit.Test;
import org.springframework.core.io.ClassPathResource;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.nio.channels.FileChannel;
import java.security.SecureRandom;

/**
 * @author John·Louis
 * @date create in 2019/10/13
 * description:
 */
public class NIOTest1 {

    @Test
    public void test() {
        IntBuffer buffer = IntBuffer.allocate(10);
        for (int i = 0; i < buffer.capacity(); i++) {
            int randomNum=new SecureRandom().nextInt(20) ;
            buffer.put(randomNum);
        }
        buffer.flip();
        while (buffer.hasRemaining()) {
            System.out.println(buffer.get());

        }

    }
    @Test
    public void test1() throws IOException {
        FileInputStream fileInputStream = new FileInputStream("D:\\workspace\\IntelliJ Idea\\netty-learnning\\src\\main\\resources\\nio.md");

        FileChannel channel = fileInputStream.getChannel();

        ByteBuffer buffer = ByteBuffer.allocate(512);
        channel.read(buffer);

        buffer.flip();

        while (buffer.remaining() > 0) {
            byte b = buffer.get();
            System.out.println("Character: " + b);
        }
    }
    @Test
    public void test2() throws IOException {
        try (FileOutputStream outputStream = new FileOutputStream("")) {
            FileChannel channel = outputStream.getChannel();

            ByteBuffer buffer = ByteBuffer.allocate(512);

            byte[] messages = "Hello world".getBytes();
            for (int i = 0; i < messages.length; i++) {
                buffer.put(messages[i]);
            }
            buffer.flip();
            channel.write(buffer);
        }
    }
//    将一个文件中的内容读取到另一个文件
    @Test
    public void test3() throws IOException {
        ClassPathResource inputResource = new ClassPathResource("input.txt");
        FileInputStream inputStream = new FileInputStream(inputResource.getFile());

        ClassPathResource outputResource = new ClassPathResource("output.txt");
        FileOutputStream outputStream = new FileOutputStream(outputResource.getFile());

        try  {
            FileChannel inputChannel = inputStream.getChannel();
            FileChannel outputChannel = outputStream.getChannel();

            ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
            while (byteBuffer.remaining() > 0) {
                byte b = byteBuffer.get();
                System.out.println("Character: " + b);
            }

//            while (true) {
////                如果注释掉这行代码会出现什么？
//                byteBuffer.clear();
////                读的字节的个数
//                int read = inputChannel.read(byteBuffer);
//                System.out.println((char) byteBuffer.get(1));
//                System.out.println(read);
//                if (-1 == read) {
//                    break;
//                }
//                System.out.println("-------");
//                System.out.println(inputChannel.isOpen());
//                System.out.println("-------");
//                byteBuffer.flip();
//                outputChannel.write(byteBuffer);
//
//            }
        }finally {
//            inputStream.close();
//            outputStream.close();
        }
    }
    @Test
    public void test4() throws IOException {

//        File[] files = Optional.ofNullable(file.listFiles()).orElse(new File[1]);
//        Arrays.stream(files)
//                .map(File::getName).forEach(System.out::println);


    }
}
