package com.linked;

import lombok.Data;

import java.io.Serializable;
import java.util.NoSuchElementException;

@Data
public class SingleLink<E extends Serializable> implements MyList<E>{

    private int size=0;

    private Node<E> first;

    private Node<E> last;

    @Override
    public void add(E e) {
        if (first == null) {
            addFirst(e);
        } else {
            addLast(e);
        }

    }


    @Override
    public E find(int index) {
        return node(index).data;
    }

    private Node<E> node(int index) {
        if (index > size - 1) {
            throw new NoSuchElementException();
        }
        Node<E> x = first;
        for (int i = 0; i < index; i++) {
            x = x.next;
        }
        return x;
    }

    public void addLast(E e) {
        Node<E> l = this.last;
        Node<E> newNode = new Node<>(e, null);
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
        Node<E> l = last;
        if (l == null) {
            throw  new NoSuchElementException();
        }
        this.last = null;
        size--;
    }

    public void addFirst(E e) {
        Node<E> f = first;
        Node<E> newNode = new Node<>(e, f);
        first = newNode;
        if (f == null) {
            last = newNode;
        }
//        如果加载头部，尾结点没有变
        size++;
    }


    @Override
    public void addIndex(int index, E e) {
        if (index > this.size) {
            throw new NoSuchElementException();
        }
        if (index == this.size) {
            addLast(e);
        } else if (index == 0) {
            addFirst(e);
        } else {
            Node<E> eleCurrent = node(index);
            Node<E> ePre = node(index - 1);
            Node<E> node = new Node<>(e, eleCurrent);
            ePre.next = node;
            size++;
        }
    }


    @Override
    public void deleteFirst() {
        Node<E> node = node(1);
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
            Node<E> pre = node(index - 1);
            Node<E> last = node(index + 1);
            pre.next = last;
            size--;
        }

    }

    @Data
    private class Node<E extends Serializable> {
        private E data;
        private Node<E> next;

        public Node(E data, Node<E> next) {
            this.data = data;
            this.next = next;
        }
    }

}
