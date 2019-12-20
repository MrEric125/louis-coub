package com.netty.withbilibili.shangguigu.nio;

import lombok.extern.slf4j.Slf4j;

import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;

/**
 * @author John·Louis
 * @date create in 2019/12/19
 * description:
 * 核心组件
 * 1.ByteBuffer
 * 2.Selector
 * 3.Channel
 *
 * 1. 每个channel 一般对应一个buffer
 * 2.一个selector 对应一个线程  一个selector 对应过个channel
 * 3.nio 网络模型，反应的是channel 注册到selector的过程
 * 4. 程序切换到哪个channel 是由事件（Event）决定的 event 只是一个概念 分工的概念
 * 5. selector 是由不同的时间在不同的通道上面切换的
 * 6. buffer就是一个内存块，底层其实就是一个数组
 * 7. 数据的读写都是通过buffer, buffer 是可以读也可以写，但是需要flip切换一下
 * 8. channel是双向的，可以范湖底层操作系统的情况，比如linux底层操作系统通道就是双向的

 */
@Slf4j
public class BasicBuffer {

    public static void main(String[] args) {
//        创建可以存放10个int的buffer
        IntBuffer buffer = IntBuffer.allocate(10);

        for (int i = 0; i < buffer.capacity(); i++) {
            buffer.put(i * 3);
        }
//        读写切换操作

        buffer.flip();

    }
}
