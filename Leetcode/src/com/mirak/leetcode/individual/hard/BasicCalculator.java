package com.mirak.leetcode.individual.hard;

import java.util.Stack;

public class BasicCalculator {
  public int calculate(String s) {
    Stack<Integer> numsStack = new Stack<>();
    Stack<Character> opStack = new Stack<>();
    int idx = 0;
    while (idx < s.length()) {
      if (s.charAt(idx) == ' ') {
        idx++;
        continue;
      }
      if (Character.isDigit(s.charAt(idx))) {
        int j = idx + 1;
        while (j < s.length() && Character.isDigit(s.charAt(j))) {
          j++;
        }
        numsStack.push(Integer.parseInt(s.substring(idx, j)));
        idx = j;
      } else if (s.charAt(idx) == '(') {
        opStack.push(s.charAt(idx));
        idx++;
      } else if (s.charAt(idx) == ')') {
        while (opStack.peek() != '(') {
          doStackOperation(numsStack, opStack);
        }
        opStack.pop();
        idx++;
      } else {
        while (!opStack.isEmpty() && !isHigherPrecedence(s.charAt(idx), opStack.peek())) {
          doStackOperation(numsStack, opStack);
        }
        opStack.push(s.charAt(idx));
        idx++;
      }
    }
    while (!opStack.isEmpty()) {
      doStackOperation(numsStack, opStack);
    }
    return numsStack.pop();
  }

  private static int doOperation(int a, int b, char op) {
    if (op == '+') {
      return a + b;
    }
    return a - b;
  }

  private static boolean isHigherPrecedence(char a, char b) {
    return b == '(';
  }

  private void doStackOperation(Stack<Integer> numsStack, Stack<Character> opStack) {
    int b = numsStack.pop();
    int a = numsStack.pop();
    numsStack.push(doOperation(a, b, opStack.pop()));
  }
}
