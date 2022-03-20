package com.luois;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.commons.collections.CollectionUtils;
import org.assertj.core.util.Lists;

import java.io.Serializable;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author John·Louis
 * @date created on 2020/2/1
 * description:
 */
public class ThreadTest {

    public static void main(String[] args) {
        ThreadTest threadTest = new ThreadTest();
        Node<Long> node = new Node<>(12L,"pay");
        Node<Long> node2 = new Node<>(13L,"start");
        Node<Long> node3 = new Node<>(14L,"start");
        List<Node<Long>> nodeList = Lists.newArrayList(node, node2, node3);

        threadTest.removeAfterExpire(nodeList,60L);

        for (Node<Long> longNode : nodeList) {
            longNode.setStatus("pay");
        }
    }
    ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(23);

    public  <K extends Serializable> void removeAfterExpire(List<Node<K>> lists, Long expireTime) {
        scheduledThreadPool.schedule(new Runnable() {
            @Override
            public void run() {
                if (CollectionUtils.isNotEmpty(lists)) {
                    for (Node<K> list : lists) {
                        if (list.status.equals("pay")) {
                            System.out.println(list.key);
                        }
                    }
                }

            }
        }, expireTime, TimeUnit.SECONDS);
    }

    @AllArgsConstructor
    @NoArgsConstructor
    @Setter
    @Getter
     public static class Node<K extends Serializable>{
         private K key;
         private String status;
    }
}
