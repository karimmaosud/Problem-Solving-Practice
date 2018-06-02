package com.mirak.leetcode.individual.hard;

import java.util.HashMap;
import java.util.Map;

public class LRUCache {

  private class ListNode {
    ListNode next;
    ListNode prev;
    int key, value;

    ListNode(int key, int value) {
      this.key = key;
      this.value = value;
      next = null;
      prev = null;
    }
  }

  private class MyDoublyLinkedList<T> {
    int size;
    ListNode head, tail;

    MyDoublyLinkedList() {
      this.head = null;
      this.tail = null;
      this.size = 0;
    }

    ListNode addLast(int key, int value) {
      if (head == null) {
        head = tail = new ListNode(key, value);
      } else {
        tail.next = new ListNode(key, value);
        tail.next.prev = tail;
        tail = tail.next;
      }
      size++;
      return tail;
    }

    void removeNode(ListNode node) {

      size--;

      ListNode prev = node.prev;
      ListNode next = node.next;

      node.next = node.prev = null;

      if (prev == null && next == null) {
        head = tail = null;
        return;
      }

      if (prev == null) {
        // node at head
        head = next;
        next.prev = null;
        return;
      }

      if (next == null) {
        // node at tail
        tail = prev;
        prev.next = null;
        return;
      }

      prev.next = next;
      next.prev = prev;
    }
  }

  private MyDoublyLinkedList<Integer> cacheList;
  private int capacity;
  private Map<Integer, ListNode> map;

  public LRUCache(int capacity) {
    cacheList = new MyDoublyLinkedList<Integer>();
    map = new HashMap<>();
    this.capacity = capacity;
  }

  public int get(int key) {
    if (!map.containsKey(key)) {
      return -1;
    }
    ListNode node = map.get(key);
    cacheList.removeNode(node);
    map.put(key, node = cacheList.addLast(key, node.value));
    return node.value;
  }

  public void put(int key, int value) {
    if (map.containsKey(key)) {
      cacheList.removeNode(map.get(key));
    }
    ListNode node = cacheList.addLast(key, value);
    map.put(key, node);
    evictFromCache();
  }

  private void evictFromCache() {
    if (cacheList.size > this.capacity) {
      ListNode deleted = cacheList.head;
      map.remove(deleted.key);
      cacheList.removeNode(deleted);
    }
  }
}
