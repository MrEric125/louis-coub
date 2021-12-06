package com.louis.coub.netty.withbilibili.shengsiyuan.nio;

import java.io.IOException;
import java.nio.channels.Selector;

/**
 * @author John·Louis
 * @date create in 2019/12/10
 * description:
 * selector 和channel还有ByteBuffer之间的关系
 * selector 功能：
 *  channel到selector的关系是通过{@link java.nio.channels.SelectionKey} 之间的关键字来关联的
 *  {@link java.nio.channels.SelectableChannel#register(Selector, int)}在注册channel的同时，也吧SelectionKey注册进来
 * selector 作用
 */
public class SelectorDemo {
    public static void main(String[] args) throws IOException {

        int[] port = new int[]{5000,5001,5002,5003,5004};

//        打开selector对象
        Selector selector = Selector.open();



    }
}
