package com.netty.inaction.chapter1;

import com.google.common.collect.Maps;
import org.assertj.core.util.Lists;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import java.util.Map;

/**
 * @author jun.liu
 * @date created on 2020/10/16
 * description:
 */
public class NetSocketIO {

    public void test1(Integer portNumber) throws IOException {

        ServerSocket serverSocket = new ServerSocket(portNumber);

        Socket client =serverSocket.accept();

        BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));

        PrintWriter out = new PrintWriter(client.getOutputStream(), true);

        String request, response;

        while ((request = in.readLine()) != null) {
            if ("done".equals(request)) {
                break;
            }
            response = process(request);

            out.println(response);
        }
    }

    public String process(String string) {
        Map<String, String> maps = Maps.newHashMap();
        String a = "";
        List<String> stringList = Lists.newArrayList();
        return "";
    }
}
