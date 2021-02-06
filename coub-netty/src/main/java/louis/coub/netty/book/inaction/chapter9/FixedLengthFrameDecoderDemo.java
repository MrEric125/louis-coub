package louis.coub.netty.book.inaction.chapter9;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import org.junit.Test;

/**
 * @author John·Louis
 * @date create in 2019/12/18
 * description:
 */
public class FixedLengthFrameDecoderDemo {

    @Test
    public void test() {
        ByteBuf buffer = Unpooled.buffer();
        for (int i =6; i < 9; i++) {
            buffer.writeInt(i);
        }

        ByteBuf input = buffer.duplicate();
        System.out.println(input.readableBytes());
//        input.
//        ByteBuf retain = input.retain();
//        for (int i = 0; i < 3; i++) {
//
//            System.out.println(retain.readByte());
//        }
//        EmbeddedChannel channel = new EmbeddedChannel(new FixedLengthFrameDecoder(3));
//        assert channel.writeInbound(input.retain());
//        assert channel.finish();
//        ByteBuf readInbound = (ByteBuf) channel.readInbound();
//        assert buffer.readSlice(3).equals(readInbound);
//


    }
}
