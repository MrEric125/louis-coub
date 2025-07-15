package com.louis.longagocode.concurrent.tools;

import javax.annotation.concurrent.ThreadSafe;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @author louis
 * <p>
 * Date: 2019/9/6
 * Description:
 * <p>
 * 跨域，jsonp,搭建一个服务，使用请求头，允许跨域
 */
@ThreadSafe
public class LinkedQueue<E> {


    private static class Node<E> {
        private final E item;
        final AtomicReference<Node<E>> next;

        public Node(E item, Node<E> next) {
            this.item = item;
            this.next = new AtomicReference<>(next);
        }

        public E get() {
            return item;
        }
    }


    private final Node<E> dummy = new Node<E>(null, null);
    private final AtomicReference<Node<E>> head =
            new AtomicReference<>(dummy);
    private final AtomicReference<Node<E>> tail =
            new AtomicReference<>(dummy);

    public boolean put(E item) {
        Node<E> newNOde = new Node<>(item, null);
        while (true) {
            Node<E> curTail = tail.get();
            Node<E> tailNext = curTail.next.get();
            if (curTail == tail.get()) {
//                检测是否处于中间状态
                if (tailNext != null) {
//                    队列处于中间状态，推进尾节点
                    tail.compareAndSet(curTail, tailNext);
                } else {
//                    处于稳定状态，插入新节点
                    if (curTail.next.compareAndSet(null, newNOde)) {
//                        插入操作成功，尝试推进尾节点;
                        tail.compareAndSet(curTail, newNOde);
                        return true;
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        LinkedQueue<String> linkedQueue = new LinkedQueue<>();
        linkedQueue.put("zhangsan");
        System.out.println(linkedQueue.head.get().item);
        System.out.println(linkedQueue.tail.get().item);

    }


}
