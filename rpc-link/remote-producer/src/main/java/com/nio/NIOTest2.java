package com.nio;

import org.junit.Test;
import scala.util.control.Exception;

import java.nio.ByteBuffer;
import java.util.Arrays;

/**
 * @author John·Louis
 * @date create in 2019/10/13
 * description:
 */
public class NIOTest2 {

    /**
     * 分片buffer
     * 左闭右开
     */
    @Test
    public void test1(){
        ByteBuffer buffer = ByteBuffer.allocate(10);
        for (int i = 0; i < buffer.capacity(); i++) {
            buffer.put((byte) i);
        }
        buffer.position(2);

        buffer.limit(6);

        ByteBuffer slice = buffer.slice();

        for (int i = 0; i < slice.capacity(); i++) {
            byte b = slice.get(i);
            b *= 2;

            slice.put(i ,b);
        }
        buffer.position(0);
        buffer.limit(buffer.capacity());
        byte[] array = buffer.array();
        for (byte b : array) {
            System.out.println(b);
        }
    }

    /**
     * 只读buffer
     */
    @Test
    public void test2() {
        ByteBuffer buffer = ByteBuffer.allocate(10);


        ByteBuffer readOnlyBuffer = buffer.asReadOnlyBuffer();

    }
}
