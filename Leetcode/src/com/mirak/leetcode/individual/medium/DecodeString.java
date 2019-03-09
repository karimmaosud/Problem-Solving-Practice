package com.mirak.leetcode.individual.medium;

import java.util.*;

public class DecodeString {

  public String decodeString(String s) {

    if (s.isEmpty()) {
      return s;
    }

    Stack<StringBuilder> stack = new Stack<>();
    Stack<Integer> factorsStack = new Stack<>();
    int idx = 0;
    while (idx < s.length()) {
      if (Character.isDigit(s.charAt(idx))) {
        int j = idx;
        int factor = 0;
        while (Character.isDigit(s.charAt(j))) {
          factor = factor * 10 + (s.charAt(j++) - '0');
        }
        idx = j;
        factorsStack.push(factor);
      } else if (s.charAt(idx) == '[') {
        stack.push(null);
        idx++;
      } else if (s.charAt(idx) != ']') {
        StringBuilder builder = new StringBuilder();
        int j = idx;
        while (j < s.length() && Character.isAlphabetic(s.charAt(j))) {
          builder.append(s.charAt(j++));
        }
        idx = j;
        stack.push(builder);
        squashStack(stack);
      } else {
        int factor = factorsStack.pop();
        StringBuilder str = stack.pop();
        int size = str.length();
        for (int i = 0; i < factor - 1; i++) {
          for (int j = 0; j < size; j++) {
            str.append(str.charAt(j));
          }
        }

        // pop the null value.
        stack.pop();

        stack.push(str);
        squashStack(stack);

        idx++;
      }
    }
    squashStack(stack);
    return stack.pop().toString();
  }

  private static void squashStack(Stack<StringBuilder> stack) {
    if (stack.isEmpty() || stack.peek() == null) {
      return;
    }
    StringBuilder right = stack.pop();
    while (!stack.isEmpty() && stack.peek() != null) {
      stack.peek().append(right);
      right = stack.pop();
    }
    stack.push(right);
  }

}
