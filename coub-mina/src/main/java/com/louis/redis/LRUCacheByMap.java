package com.louis.redis;

import java.util.HashMap;
import java.util.Map;

/**
 * @description:
 * @author: Louis
 * 内置头和尾节点，链表节点定义为内部类
 * 每天添加数据的时候，新增一个Node,这个Node的下一个指向之前的头结点，然后判断情况，是否要删除尾节点
 */
class LRUCacheByMap {
    // 双向链表节点定义
    class Node {
        int key, value;
        Node prev, next;
        Node(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    private final int capacity;
    private final Map<Integer, Node> map;
    private final Node head, tail; // 虚拟头尾节点（简化边界处理）

    public LRUCacheByMap(int capacity) {
        this.capacity = capacity;
        this.map = new HashMap<>();
        this.head = new Node(0, 0);
        this.tail = new Node(0, 0);
        head.next = tail;
        tail.prev = head;
    }

    public int get(int key) {
        if (!map.containsKey(key)) return -1;
        Node node = map.get(key);
        removeNode(node);   // 从原位置移除
        addToHead(node);    // 移动到头部（标记为最近使用）
        return node.value;
    }

    public void put(int key, int value) {
        if (map.containsKey(key)) {
            Node node = map.get(key);
            node.value = value;
            removeNode(node);
            addToHead(node);
        } else {
            if (map.size() == capacity) {
                Node last = tail.prev; // 尾部节点（最久未使用）
                removeNode(last);
                map.remove(last.key);  // 同步删除哈希表中的键
            }
            Node newNode = new Node(key, value);
            addToHead(newNode);
            map.put(key, newNode);
        }
    }

    // 将节点插入链表头部
    private void addToHead(Node node) {
        node.next = head.next;
        node.prev = head;
        head.next.prev = node;
        head.next = node;
    }

    /**
     * 删除节点,逻辑，将关于这个节点的引用关系给删掉
     * @param node
     */
    private void removeNode(Node node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }


    public static void main(String[] args) {
        LRUCacheByMap cache = new LRUCacheByMap(2);

        for (int i = 1; i <= 3; i++) {
            cache.put(i, i);
        }
        System.out.println(cache);

    }
}
