package com.luois.nio;

import org.junit.Test;

import java.nio.ByteBuffer;

/**
 * @author Louis
 * @date created on 2021/1/23
 * description:
 */
public class ByteBufferDemo {

    @Test
    public void test() {

        ByteBuffer buffer = ByteBuffer.allocate(50);
        System.out.println(buffer.toString());
        buffer.put("2222".getBytes());
        buffer.put("333333".getBytes());
        buffer.put("444444".getBytes());
        buffer.put("555555".getBytes());
        System.out.println(buffer.toString());

        buffer.put("11".getBytes());
        buffer.put("22".getBytes());
        buffer.put("33".getBytes());
        buffer.flip();
        System.out.println(buffer.get());
        System.out.println(buffer.toString());
        ByteBuffer slice = buffer.slice();
        System.out.println(slice.toString());
        System.out.println(new String(slice.array()));
//        while (buffer.hasRemaining()) {
//            System.out.print(buffer.get());
//        }

    }
}
