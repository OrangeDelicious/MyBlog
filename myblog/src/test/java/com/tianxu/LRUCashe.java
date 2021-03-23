package com.tianxu;



import java.util.HashMap;
import java.util.Map;
import java.util.Arrays;

class LRUCache {

    class Node {
        int key;
        int val;
        Node pre;
        Node next;
        public Node() {}
        public Node (int key, int val) {
            this.key = key;
            this.val = val;
            pre = null;
            next = null;
        }
    }

    Map<Integer,Node> map;
    int capacity;
    int size;
    Node dummyHead;
    Node dummyTail;
    public LRUCache(int capacity) {
        this.size = 0;
        this.map = new HashMap<>();
        this.dummyHead = new Node();
        this.dummyTail = new Node();
        dummyHead.next = dummyTail;
        dummyTail.pre = dummyHead;
        this.capacity = capacity;
    }

    public int get(int key) {
        if (map.containsKey(key)) {
            Node node = map.get(key);
            Node pre = dummyHead;
            while (pre.next != node) {
                pre = pre.next;
            }
            node.next.pre = pre;
            pre.next = node.next;
            insertHead(node);
            return node.val;
        } else {
            return -1;
        }

    }

    public void put(int key, int value) {
        if (map.containsKey(key)) {
            Node node = map.get(key);
            node.val = value;
            Node pre = dummyHead;
            while (pre.next != node) {
                pre = pre.next;
            }
            node.next.pre = pre;
            pre.next = node.next;
            insertHead(node);
        } else {
            Node node = new Node(key,value);
            map.put(key,node);
            insertHead(node);
            size++;
            if (size > capacity) {
                deleteTail(key);
                size--;
            }
        }
    }

    public void insertHead(Node node) {
        node.next = dummyHead.next;
        dummyHead.next.pre = node;
        node.pre = dummyHead;
        dummyHead.next = node;
    }
    public void deleteTail(int key) {
        Node node = dummyTail.pre;
        dummyTail.pre = node.pre;
        node.pre.next = dummyTail;
        map.remove(key);
    }

    public static void main(String[] args) {
        LRUCache lruCache = new LRUCache(2);
        lruCache.put(1,1);
        lruCache.put(2,2);
        lruCache.get(1);
        lruCache.put(3,3);
        lruCache.get(2);
        lruCache.put(4,4);
        lruCache.get(1);
        lruCache.get(3);
        lruCache.get(4);

    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */
