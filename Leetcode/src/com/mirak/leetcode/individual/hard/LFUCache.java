package com.mirak.leetcode.individual.hard;

import java.util.HashMap;
import java.util.Map;

public class LFUCache {

  private class ListNode {
    int key, value, freq;
    ListNode next, prev;
    ListNode(int key, int value, int freq) {
      this.key = key;
      this.value = value;
      this.freq = freq;
      this.next = null;
      this.prev = null;
    }
  }

  private class MyLinkedList {
    int size;
    ListNode head, tail;

    MyLinkedList() {
      this.head = this.tail = null;
      this.size = 0;
    }

    void insertAtTail(ListNode node) {
      this.size++;
      if(this.head == null) {
        this.head = this.tail = node;
      }else {
        this.tail.next = node;
        node.prev = this.tail;
        this.tail = node;
      }
    }

    void deleteNode(ListNode node) {
      this.size--;
      ListNode next = node.next;
      ListNode prev = node.prev;

      if(node == this.head) {
        if(this.head == this.tail) {
          this.head = this.tail = null;
        }else {
          next.prev = null;
          this.head = next;
        }
      }else if(node == tail) {
        prev.next = null;
        this.tail = prev;
      }else {
        prev.next = next;
        next.prev = prev;
      }
      node.next = node.prev = null;
    }
  }

  private Map<Integer, ListNode> nodeMap;
  private Map<Integer, MyLinkedList> freqMap;
  private int capacity;
  private int minFreq;
  private int cacheNodes;
  public LFUCache(int capacity) {
    this.nodeMap = new HashMap<>();
    this.freqMap = new HashMap<>();
    this.capacity = capacity;
    this.minFreq = Integer.MAX_VALUE;
    this.cacheNodes = 0;
  }

  public int get(int key) {
    if(!nodeMap.containsKey(key)) {
      return -1;
    }

    ListNode node = nodeMap.get(key);
    updateCacheKey(node.key, node.value);

    return node.value;

  }

  public void put(int key, int value) {

    if(this.capacity == 0) {
      return;
    }

    if(!nodeMap.containsKey(key)) {
      if(this.cacheNodes == capacity) {
        this.cacheNodes--;
        MyLinkedList list = this.freqMap.get(this.minFreq);
        nodeMap.remove(list.head.key);
        list.deleteNode(list.head);
      }

      if(!freqMap.containsKey(1)) {
        freqMap.put(1, new MyLinkedList());
      }

      ListNode inserted = new ListNode(key, value, 1);
      freqMap.get(1).insertAtTail(inserted);
      nodeMap.put(key, inserted);

      this.minFreq = 1;
      this.cacheNodes++;
    }else {
      updateCacheKey(key, value);
    }
  }

  private void updateCacheKey(int key, int value) {
    ListNode node = this.nodeMap.get(key);
    node.value = value;
    MyLinkedList list = freqMap.get(node.freq);
    list.deleteNode(node);
    node.freq++;

    if(!freqMap.containsKey(node.freq)) {
      freqMap.put(node.freq, new MyLinkedList());
    }

    freqMap.get(node.freq).insertAtTail(node);

    if(list.size == 0) {
      if(minFreq == node.freq - 1) {
        minFreq = node.freq;
      }
    }
  }
}
