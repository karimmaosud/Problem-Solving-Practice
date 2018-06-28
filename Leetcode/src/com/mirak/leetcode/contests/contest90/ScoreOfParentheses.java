package com.mirak.leetcode.contests.contest90;

import java.util.Stack;

public class ScoreOfParentheses {
  public int scoreOfParentheses(String S) {
    Stack<Integer> stack = new Stack<>();
    Stack<Integer> additionStack = new Stack<>();
    int[] value = new int[S.length()];
    for(int i = 0; i < S.length(); i++) {
      if(S.charAt(i) == '(') {
        stack.add(i);
      }else {
        int left = stack.pop();
        if(i == left + 1) {
          value[left] = 1;
          additionStack.add(left);
          continue;
        }
        int multiply = 0;
        while (!additionStack.isEmpty() && additionStack.peek() > left) {
          multiply += value[additionStack.pop()];
        }
        value[left] = 2 * multiply;
        additionStack.add(left);
      }
    }
    int res = 0;
    while(!additionStack.isEmpty()) {
      res += value[additionStack.pop()];
    }
    return res;
  }
}
