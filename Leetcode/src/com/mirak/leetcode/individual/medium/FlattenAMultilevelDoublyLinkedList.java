package com.mirak.leetcode.individual.medium;

public class FlattenAMultilevelDoublyLinkedList {

  private class Node {

    Node next, prev, child;
  }

  public Node flatten(Node head) {
    Node current = head;
    while (current != null) {
      if (current.child == null) {
        current = current.next;
        continue;
      }
      Node moved = current.child;
      while (moved.next != null) {
        moved = moved.next;
      }

      Node temp = current.next;
      current.next = current.child;
      current.next.prev = current;
      current.child = null;
      if (temp != null) {
        moved.next = temp;
        temp.prev = moved;
      }

      current = current.next;
    }
    return head;
  }

}
