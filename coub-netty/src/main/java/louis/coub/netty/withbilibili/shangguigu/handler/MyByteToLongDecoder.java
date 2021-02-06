package louis.coub.netty.withbilibili.shangguigu.handler;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;
import java.util.Optional;

/**
 * @author John·Louis
 * @date create in 2019/12/10
 * description:
 */
public class MyByteToLongDecoder extends ByteToMessageDecoder {
    /**
     *
     * @param ctx 上下文对象
     * @param in 入栈的byteBuf
     * @param out 将解码的数据放在这个集合中交给下一个handler处理
     * @throws Exception
     */
    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        ByteBuf byteBuf = Optional.ofNullable(in).filter(x -> x.readableBytes() >= 8).get();

        out.add(byteBuf.readLong());


    }
}
