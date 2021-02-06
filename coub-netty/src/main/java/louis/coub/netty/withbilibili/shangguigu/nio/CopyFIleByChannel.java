package louis.coub.netty.withbilibili.shangguigu.nio;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.ReadableByteChannel;

/**
 * @author John·Louis
 * @date create in 2019/12/21
 * description:
 */
public class CopyFIleByChannel {

    public static void main(String[] args) throws IOException {
        FileInputStream inputStream = new FileInputStream("1.txt");
        FileChannel inputStreamChannel = inputStream.getChannel();
        FileOutputStream fileOutputStream = new FileOutputStream("2.txt");
        FileChannel outputStreamChannel = fileOutputStream.getChannel();
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        while (true) {
            buffer.clear();
            int read = inputStreamChannel.read(buffer);
            if (read==-1) {
                break;
            }
            buffer.flip();
            outputStreamChannel.write(buffer);
        }


        inputStream.close();
        fileOutputStream.close();

    }

    public static void transforFrom(ReadableByteChannel src, FileChannel desc, long size) throws IOException {
        desc.transferFrom(src, 0, size);
    }
}
