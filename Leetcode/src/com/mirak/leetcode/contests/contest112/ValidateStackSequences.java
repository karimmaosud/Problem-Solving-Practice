package com.mirak.leetcode.contests.contest112;

import java.util.*;

public class ValidateStackSequences {
  public boolean validateStackSequences(int[] pushed, int[] popped) {
    Stack<Integer> stack = new Stack<>();
    int i = 0, j = 0;
    while (i < pushed.length && j < popped.length) {
      if (!stack.isEmpty() && stack.peek() == popped[j]) {
        stack.pop();
        j++;
        continue;
      }
      stack.push(pushed[i++]);
    }

    if (j == popped.length) {
      return true;
    }

    while (j < popped.length) {
      if (stack.isEmpty() || stack.peek() != popped[j]) {
        return false;
      }
      stack.pop();
      j++;
    }
    return true;
  }
}
