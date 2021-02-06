package louis.coub.netty.nio.channel;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;

/**
 * @author louis
 * <p>
 * Date: 2019/12/9
 * Description:
 * 虚拟内存映射
 */
public class VirtualMemoryMapping {

    public static void main(String[] args) throws IOException {
        File temp = File.createTempFile("holy", null);
        RandomAccessFile randomAccessFile = new RandomAccessFile(temp, "rw");
        FileChannel fileChannel = randomAccessFile.getChannel();
//        fileChannel.map()
    }
}
