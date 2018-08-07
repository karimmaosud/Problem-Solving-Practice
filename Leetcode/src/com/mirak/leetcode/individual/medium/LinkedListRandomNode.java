package com.mirak.leetcode.individual.medium;

import java.util.Random;

public class LinkedListRandomNode {

  private class ListNode {

    int val;
    ListNode next;

    ListNode(int x) {
      val = x;
    }
  }

  class Solution {
    ListNode head;
    int size;
    Random random;
    /** @param head The linked list's head.
    Note that the head is guaranteed to be not null, so it contains at least one node. */
    public Solution(ListNode head) {
      this.head = head;
      this.size = 0;
      ListNode runner = head;
      while(runner != null) {
        runner = runner.next;
        size++;
      }
      random = new Random();
    }

    /** Returns a random node's value. */
    public int getRandom() {

      int randomIndex = random.nextInt(this.size);
      int runnerIndex = 0;
      ListNode node = this.head;
      while (runnerIndex != randomIndex) {
        node = node.next;
        runnerIndex++;
      }
      return node.val;
    }
  }
}
