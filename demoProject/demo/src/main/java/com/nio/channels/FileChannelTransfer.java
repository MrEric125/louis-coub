package com.nio.channels;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.channels.FileChannel;
import java.nio.channels.WritableByteChannel;
import java.util.Arrays;

/**
 * @author jun.liu
 * @since 2021/1/21 16:57
 */
public class FileChannelTransfer {
    public static void main(String[] argv) throws Exception {
        String[] iF = new String[]{"D:\\ideaworkspace\\louis\\boot-mybatis\\demo\\src\\main\\java\\com\\nio\\channels\\input1.txt",
                "D:\\ideaworkspace\\louis\\boot-mybatis\\demo\\src\\main\\java\\com\\nio\\channels\\input2.txt"};

        String oF = "D:\\ideaworkspace\\louis\\boot-mybatis\\demo\\src\\main\\java\\com\\nio\\channels\\data.txt";

        FileOutputStream output = new FileOutputStream(new File(oF));
        WritableByteChannel targetChannel = output.getChannel();

        Arrays.stream(iF).forEach(item->{
            try {

                FileInputStream input = new FileInputStream(item);
                FileChannel inputChannel = input.getChannel();

                inputChannel.transferTo(0, inputChannel.size(), targetChannel);

                inputChannel.close();
                input.close();
            } catch (Exception e) {
                e.printStackTrace();
            }

        });

        targetChannel.close();
        output.close();
    }
}
