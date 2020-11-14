package com.netty.withbilibili.nonetty.nio;

import org.junit.Test;

import java.io.IOException;
import java.nio.channels.Selector;

/**
 * @author John·Louis
 * @date create in 2019/10/21
 * description:
 *
 * 关于selector
 */
public class NioTest4 {

    @Test
    public void test1() throws IOException {

        int[] ports = new int[5];
        ports[0] = 5000;
        ports[1] = 5001;
        ports[2] = 5002;
        ports[3] = 5003;
        ports[4] = 5004;

        Selector selector = Selector.open();



    }
}
