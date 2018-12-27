package com.mirak.leetcode.individual.medium;

public class PlusOneLinkedList {

  private class ListNode {

    ListNode next;
    int val;

    ListNode(int val) {
      this.val = val;
    }
  }

  public ListNode plusOne(ListNode head) {
    int carry = addOne(head);
    if (carry == 1) {
      ListNode newHead = new ListNode(1);
      newHead.next = head;
      return newHead;
    }

    return head;
  }

  private int addOne(ListNode node) {
    if (node.next == null) {
      return incrementNodeValue(node);
    }

    int carry = addOne(node.next);
    if (carry == 1) {
      return incrementNodeValue(node);
    }
    return 0;
  }

  private int incrementNodeValue(ListNode node) {
    node.val++;
    if (node.val == 10) {
      node.val = 0;
      return 1;
    }
    return 0;
  }
}
