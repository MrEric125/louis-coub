package com.louis.coub.netty.withbilibili.shangguigu.handler;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import lombok.extern.slf4j.Slf4j;

/**
 * @author John·Louis
 * @date create in 2019/12/10
 * description:
 */
@Slf4j
public class MyLongToByteEncoder extends MessageToByteEncoder<Long> {

    @Override
    protected void encode(ChannelHandlerContext ctx, Long msg, ByteBuf out) throws Exception {
        log.info("MyLongToByteEncoder encoder 被调用");
        log.info("message:{}", msg);
        out.writeLong(msg);
    }
}
