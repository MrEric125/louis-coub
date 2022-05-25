package com.louis.mybatis.provider;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import sun.misc.Unsafe;

import java.io.IOException;
import java.lang.instrument.Instrumentation;
import java.lang.reflect.Field;

/**
 * @author louis
 * <p>
 * Date: 2019/9/23
 * Description:
 */
@Slf4j
public class MapperTest {


    private static final Unsafe unsafe;
    /**
     * Size of any Object reference
     */
    private static final int objectRefSize;

    static {
        try {
            Field field = Unsafe.class.getDeclaredField("theUnsafe");
            field.setAccessible(true);
            unsafe = (Unsafe) field.get(null);

            // 可以通过Object[]数组得到oop指针究竟是压缩后的4个字节还是未压缩的8个字节
            objectRefSize = unsafe.arrayIndexScale(Object[].class);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    /**
     * 计算对象的偏移量
     * @param args
     */
    public static void main(String[] args) throws IOException {

    }



}
