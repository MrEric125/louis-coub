package com.louis.coub.netty.nio.channel;

import com.google.common.collect.Lists;

import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.GatheringByteChannel;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Random;

/**
 * @author louis
 * <p>
 * Date: 2019/12/9
 * Description:
 * Demonstrate gathering write using many buffers.
 * // TODO: 2019/12/9  gather、Scatter的功能点，以及使用场景分别是什么？
 */
public class ScatterAndGather {

    private static final String DEMOGRAPHIC = "test.txt";

    public static void main(String[] args) throws Exception {
        int reps = 10;

        if (args.length > 0) {
            reps = Integer.parseInt(args[0]);
        }
        FileOutputStream outputStream = new FileOutputStream(DEMOGRAPHIC);
        GatheringByteChannel gatherChannel = outputStream.getChannel();
        ByteBuffer[] buffer = utterBS(reps);
        // Deliver the message to the waiting market
        while (gatherChannel.write(buffer) > 0) {
            //Loop until write() return zero
        }
        System.out.println(" mind share paradigms synergized to " + DEMOGRAPHIC);
        outputStream.close();

    }

    // ------------------------------------------------
    // These are just representative; add your own
    private static String[] col1 = {
            "Aggregate", "Enable", "Leverage", "Facilitate",
            "Synergize", "Repurpose", "Strategize", "Reinvent", "Harness"};
    private static String[] col2 = {
            "cross-platform", "best-of-breed", "frictionless",
            "ubiquitous", "extensible",
            "compelling", "mission-critical", "collaborative", "integrated"};
    private static String[] col3 = {
            "methodologies", "infomediaries", "platforms", "schemas", "mindshare",
            "paradigms", "functionalities", "web services", "infrastructures"};
    private static String newline = System.getProperty("line.separator");

    private static ByteBuffer[] utterBS(int howMany) throws Exception {
        List<ByteBuffer> list = Lists.newLinkedList();
        for (int i = 0; i < howMany; i++) {
            list.add(pickRandom(col1, "\n"));
            list.add(pickRandom(col2, "\n"));
            list.add(pickRandom(col3, "\n"));
        }
        ByteBuffer[] buffers = new ByteBuffer[list.size()];
        list.toArray(buffers);
        return buffers;
    }

    private static Random random = new Random();

    // Pick one, make a buffer to hold it and the suffix, load it with
// the byte equivalent of the strings (will not work properly for
// non-Latin characters), then flip the loaded buffer so it's ready
// to be drained
    private static ByteBuffer pickRandom(String[] strings, String suffix) throws Exception {
        String string = strings[random.nextInt(strings.length)];
        int total = string.length() + suffix.length();
        ByteBuffer buf = ByteBuffer.allocate(total);
        buf.put(string.getBytes(StandardCharsets.UTF_8));
        buf.put(suffix.getBytes(StandardCharsets.UTF_8));
        buf.flip();
        return (buf);
    }


}
