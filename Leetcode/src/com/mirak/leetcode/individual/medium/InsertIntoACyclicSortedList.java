package com.mirak.leetcode.individual.medium;

public class InsertIntoACyclicSortedList {

  private class Node {

    int val;
    Node next;

    Node(int val) {

    }
  }

  public Node insert(Node head, int insertVal) {
    Node newNode = new Node(insertVal);
    if (head == null) {
      newNode.next = newNode;
      return newNode;
    }

    Node runner = head.next;
    Node prev = head.val <= insertVal ? head : null;
    Node tail = head;
    while (runner != head) {
      if (runner.val <= insertVal) {
        if (prev == null || runner.val >= prev.val) {
          prev = runner;
        }
      }
      tail = runner.val > tail.val ? runner : tail;
      runner = runner.next;
    }
    if (prev == null) {
      insertNode(tail, newNode);
    } else {
      insertNode(prev, newNode);
    }
    return head;
  }

  private void insertNode(Node prev, Node newNode) {
    Node next = prev.next;
    prev.next = newNode;
    newNode.next = next;
  }

}
