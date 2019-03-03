package com.mirak.leetcode.contests.contest126;

import java.util.*;

public class CheckIfWordIsValidAfterSubstitutions {

  public boolean isValid(String S) {
    Stack<Character> stack = new Stack<>();
    for (char a : S.toCharArray()) {
      stack.push(a);
      if (stack.size() >= 3) {
        bubbleChars(stack);
      }
    }
    return stack.isEmpty();
  }

  private void bubbleChars(Stack<Character> stack) {
    char[] expected = {'c', 'b', 'a'};
    int idx;
    for (idx = 0; idx < expected.length; idx++) {
      if (stack.peek() != expected[idx]) {
        break;
      }
      stack.pop();
    }
    if (idx == expected.length) {
      return;
    }
    for (idx = idx - 1; idx >= 0; idx--) {
      stack.push(expected[idx]);
    }
  }
}
