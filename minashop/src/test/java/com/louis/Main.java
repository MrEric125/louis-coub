package com.louis;

import java.util.*;

/**
 * @author John·Louis
 * @date created on 2020/2/26
 * description:
 */
public class Main {

    public static void main(String[] args) {
        List<String> lists = null;
        System.out.println(Optional.ofNullable(lists).orElse(new ArrayList<>()));

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("ttttt");
            }
        });
        thread.start();


        HashMap<String, String> map = new HashMap<>();


        LinkedList<String> linkList = new LinkedList<>();
        linkList.add(1,"ss");
    }
}
