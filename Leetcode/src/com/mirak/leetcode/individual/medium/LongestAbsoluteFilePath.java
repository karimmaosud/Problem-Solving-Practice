package com.mirak.leetcode.individual.medium;

import java.util.*;

public class LongestAbsoluteFilePath {

  public int lengthLongestPath(String input) {
    String[] lines = input.split("\\n");
    Stack<Integer> stack = new Stack<>();
    int max = 0;
    for (String line : lines) {
      int tabCount = getTabCount(line);
      while (stack.size() > tabCount) {
        stack.pop();
      }
      int peekLength = stack.isEmpty() ? 0 : stack.peek();
      if (line.indexOf('.') != -1) {
        max = Math.max(max, peekLength + line.length() - tabCount + (tabCount > 0 ? 1 : 0));
      } else {
        stack.push(peekLength + line.length() - tabCount + (tabCount > 0 ? 1 : 0));
      }
    }
    return max;
  }

  private int getTabCount(String str) {
    int count = 0;
    for (int i = 0; i < str.length(); i++) {
      if (str.charAt(i) != '\t') {
        return count;
      }
      count++;
    }
    return count;
  }
}
