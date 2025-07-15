package com.louis.coub.netty.nio.channel;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.channels.DatagramChannel;
import java.util.Iterator;
import java.util.List;

/**
 * @author louis
 * <p>
 * Date: 2019/12/11
 * Description:
 */
public class TimeClientSocket {

    private static final int DEFAULT_TIME_PORT = 37;

    private static final long DIFF_1900 = 2208988800L;

    protected int port = DEFAULT_TIME_PORT;
    protected List remoteHosts;
    protected DatagramChannel channel;

    public TimeClientSocket(String[] arg) throws Exception {

        if (arg.length == 0) {
            throw new Exception("Usage: [ -p port ] host ...");
        }
        this.channel = DatagramChannel.open();
    }

    protected InetSocketAddress receivePacket(DatagramChannel channel, ByteBuffer buffer) throws IOException {
        buffer.clear();
        return (InetSocketAddress) channel.receive(buffer);
    }

    protected void sendRequests() throws IOException {
        ByteBuffer buffer = ByteBuffer.allocate(1);
        Iterator iterator = remoteHosts.iterator();
        while (iterator.hasNext()) {
            InetSocketAddress sa = (InetSocketAddress) iterator.next();
            System.out.println("Requesting time from " + sa.getHostName() + ":" + sa.getPort());

            buffer.clear().flip();

            channel.send(buffer, sa);
        }
    }

    public void getReplies() {
        ByteBuffer buffer = ByteBuffer.allocate(8);
        buffer.order(ByteOrder.BIG_ENDIAN);
        buffer.putLong(0, 0);
        buffer.position(4);
        ByteBuffer slice = buffer.slice();
        int expect = remoteHosts.size();
        int replies = 0;

    }

}

