package com.mirak.leetcode.contests.contest89;

import java.util.HashMap;
import java.util.Map;

public class ExamRoom {
  private class ListNode {
    int seat;
    ListNode next;
    ListNode prev;
    ListNode(int seat) {
      this.seat = seat;
      this.next = this.prev = null;
    }
  }
  private class MyLinkedList {
    ListNode head, tail;
    int size;
    MyLinkedList() {
      this.head = this.tail = null;
      this.size = 0;
    }

    void delete(ListNode node) {
      ListNode prev = node.prev;
      ListNode next = node.next;
      this.size--;

      if(prev == null && next == null) {
        head = tail = null;
      }
      else if(prev == null) {
        // node is at head
        next.prev = null;
        head = next;
      }
      else if(next == null) {
        // node at tail
        prev.next = null;
        tail = prev;
      }
      else {
        prev.next = next;
        next.prev = prev;
      }
    }

    void insertAtHead(ListNode node) {
      this.size++;
      if(head == null) {
        head = tail = node;
      }else {
        head.prev = node;
        node.next = head;
        head = node;
      }
    }

    void insertAtTail(ListNode node) {
      this.size++;
      if(tail == null) {
        head = tail = node;
      }else {
        tail.next = node;
        node.prev = tail;
        tail = node;
      }
    }

    void insertAfter(ListNode prevNode, ListNode node) {
      this.size++;
      ListNode next = prevNode.next;
      if(next == null) {
        insertAtTail(node);
      }else {
        prevNode.next = node;
        node.prev = prevNode;
        node.next = next;
        next.prev = node;
      }
    }

    boolean isEmpty() {
      return this.size == 0;
    }

  }

  private MyLinkedList seats;
  private int n;
  private Map<Integer, ListNode> seatNumberToListNode;
  public ExamRoom(int N) {
    seats = new MyLinkedList();
    this.n = N;
    seatNumberToListNode = new HashMap<>();
  }

  public int seat() {
    if(seats.isEmpty()) {
      ListNode node = new ListNode(0);
      seats.insertAtHead(node);
      seatNumberToListNode.put(0, node);
      return 0;
    }else {
      int maxDist = Math.abs(0 - seats.head.seat) - 1;
      ListNode insertAfter = null;
      ListNode runner = seats.head;
      while(runner.next != null) {
        int newSeat = (runner.seat + runner.next.seat) / 2;
        int diff = Math.min(newSeat - runner.seat - 1, runner.next.seat - newSeat - 1);
        if(diff > maxDist) {
          maxDist = diff;
          insertAfter = runner;
        }
        runner = runner.next;
      }

      if(Math.abs(n - 1 - runner.seat) - 1 > maxDist) {
        insertAfter = runner;
      }
      int newSeat;
      if(insertAfter == null) {
        //insert at head / seat 0
        newSeat = 0;
      }else if(insertAfter == seats.tail) {
        // insert at tail / seat n - 1
        newSeat = n - 1;
      }else {
        // get seat number and insert after node
        newSeat = (insertAfter.next.seat + insertAfter.seat ) / 2;
      }
      ListNode node = new ListNode(newSeat);
      if(newSeat == 0) {
        seats.insertAtHead(node);
      }else if(newSeat == n - 1) {
        seats.insertAtTail(node);
      }else {
        seats.insertAfter(insertAfter, node);
      }
      seatNumberToListNode.put(newSeat, node);
      return newSeat;
    }
  }

  public void leave(int p) {
    ListNode del = seatNumberToListNode.get(p);
    seats.delete(del);
    seatNumberToListNode.remove(del);
  }
}
