import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
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

        Arrays.stream(new byte[][]{buffer.array()}).forEach(System.out::println);
    }

    /**
     *  allocate() and allocateDirect()
     * @see java.nio.DirectByteBuffer
w
     * @throws IOException
     */
    @Test
    public void test2() throws IOException {

        try (FileInputStream inputStream = new FileInputStream("input2.txt");
             FileOutputStream outputStream = new FileOutputStream("output2.txt")) {
            FileChannel inputChannel = inputStream.getChannel();
            FileChannel outputChannel = outputStream.getChannel();

            ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
//            ByteBuffer.allocateDirect()

            while (true) {
//                如果注释掉这行代码会出现什么？
                byteBuffer.clear();
//                读的字节的个数
                int read = inputChannel.read(byteBuffer);

                System.out.println(read);

                if (-1 == read) {
                    break;
                }
                byteBuffer.flip();
                outputChannel.write(byteBuffer);

            }
        }

    }
}
