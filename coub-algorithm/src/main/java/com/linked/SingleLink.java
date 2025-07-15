package com.linked;

import lombok.Data;

import java.util.NoSuchElementException;

@Data
public class SingleLink implements MyList<Integer> {

    private int size = 0;

    private Node first;

    private Node last;

    @Override
    public void add(Integer e) {
        if (first == null) {
            addFirst(e);
        } else {
            addLast(e);
        }

    }

    public Node getFirst() {
        return first;
    }


    public void compare(Node node1, Node node2, SingleLink singleLink) {
        if (node1 == null && node2 == null) {
            return;
        }
        if (node1 == null) {
            singleLink.add(node2.data);
            compare(node1, node2.next, singleLink);
            return;
        }
        if (node2 == null) {
            singleLink.add(node1.data);
            compare(node1.next, node2, singleLink);
            return;
        }

        if (node1.data - node2.data > 0) {
            singleLink.add(node2.data);
            compare(node1, node2.next, singleLink);

        } else {
            singleLink.add(node1.data);
            compare(node1.next, node2, singleLink);
        }
    }


    @Override
    public Integer find(int index) {
        return node(index).data;
    }

    private Node node(int index) {
        if (index > size - 1) {
            throw new NoSuchElementException();
        }
        Node x = first;
        for (int i = 0; i < index; i++) {
            x = x.next;
        }
        return x;
    }


    public void addLast(Integer e) {
        Node l = this.last;
        Node newNode = new Node(e, null);
        this.last = newNode;
        if (l == null) {
            first = newNode;
        } else {
            // 原先的最后一个节点变成了倒数第二个节点
            l.next = newNode;
        }
        size++;
    }

    public void deleteLast() {
        Node l = last;
        if (l == null) {
            throw new NoSuchElementException();
        }
        this.last = null;
        size--;
    }

    public void addFirst(Integer e) {
        Node f = first;
        Node newNode = new Node(e, f);
        first = newNode;
        if (f == null) {
            last = newNode;
        }
//        如果加载头部，尾结点没有变
        size++;
    }


    @Override
    public void addIndex(int index, Integer e) {
        if (index > this.size) {
            throw new NoSuchElementException();
        }
        if (index == this.size) {
            addLast(e);
        } else if (index == 0) {
            addFirst(e);
        } else {
            Node eleCurrent = node(index);
            Node ePre = node(index - 1);
            Node node = new Node(e, eleCurrent);
            ePre.next = node;
            size++;
        }
    }


    @Override
    public void deleteFirst() {
        Node node = node(1);
        first = node;
        size--;

    }

    @Override
    public void deleteIndex(int index) {
        if (index > this.size - 1) {
            throw new NoSuchElementException();
        }
        if (index == 0) {
            deleteFirst();
        } else if (index == size - 1) {
            deleteLast();
        } else {
            Node pre = node(index - 1);
            Node last = node(index + 1);
            pre.next = last;
            size--;
        }

    }

    @Data
    private class Node {
        private Integer data;
        private Node next;

        public Node(Integer data, Node next) {
            this.data = data;
            this.next = next;
        }
    }

}
