package com.mirak.leetcode.individual.medium;


public class LinkedListCycleII {

  private class ListNode {

    ListNode next;
    int val;
  }

  public ListNode detectCycle(ListNode head) {
    ListNode slowRunner = head;
    ListNode fastRunner = head;
    while (slowRunner != null && fastRunner != null) {
      slowRunner = slowRunner.next;
      if (fastRunner.next == null) {
        return null;
      }
      fastRunner = fastRunner.next.next;
      if (fastRunner == slowRunner) {
        break;
      }
    }

    if (fastRunner == null || slowRunner == null) {
      return null;
    }

    slowRunner = head;
    while (slowRunner != fastRunner) {
      slowRunner = slowRunner.next;
      fastRunner = fastRunner.next;
    }
    return slowRunner;
  }
}


