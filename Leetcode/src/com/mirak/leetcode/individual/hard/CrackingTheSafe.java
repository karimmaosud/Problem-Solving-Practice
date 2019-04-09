package com.mirak.leetcode.individual.hard;

import java.util.*;

public class CrackingTheSafe {

  public String crackSafe(int n, int k) {
    return crackSafeEuler(n, k);
  }

  private String crackSafeGreedy(int n, int k) {
    Map<String, Integer> map = new HashMap<>();
    StringBuilder builder = new StringBuilder();
    for (int i = 0; i < n; i++) {
      builder.append('0');
    }
    generateString(builder, n, k, map);
    return builder.toString();
  }

  private void generateString(StringBuilder builder, int n, int k, Map<String, Integer> map) {
    if (builder.length() - n + 1 == (int) Math.pow(k, n)) {
      return;
    }
    String prev = builder.substring(builder.length() - n + 1);
    builder.append(map.getOrDefault(prev, 1));
    map.put(prev, (map.getOrDefault(prev, 1) + 1) % k);
    generateString(builder, n, k, map);
  }

  public String crackSafeEuler(int n, int k) {
    StringBuilder res = new StringBuilder();
    if (n == 1) {
      for (int i = 0; i < k; ++i) {
        res.append(i);
      }
      return res.toString();
    }
    StringBuilder start = new StringBuilder();
    for (int i = 0; i < n - 1; ++i) {
      start.append('0');
    }
    Stack<String> stack = new Stack<>();
    Map<String, Integer> nodeMask = new HashMap<>();
    LinkedList<String> list = new LinkedList<>();
    stack.push(start.toString());

    while (!stack.isEmpty()) {
      String current = stack.peek();
      int mask = nodeMask.getOrDefault(current, 0);
      if (mask == (1 << k) - 1) {
        stack.pop();
        list.addLast(current);
        continue;
      }
      int next = getUnvisitedEdge(mask);
      String nextState = current.substring(1) + next;
      stack.push(nextState);
      nodeMask.put(current, mask | (1 << next));
    }

    res.append(list.removeLast());
    while (!list.isEmpty()) {
      res.append(list.removeLast().charAt(n - 2));
    }
    return res.toString();
  }

  private static int getUnvisitedEdge(int mask) {
    mask++;
    return (int) (Math.log10(1.0 * (mask & -mask)) / Math.log10(2.0));
  }

}
