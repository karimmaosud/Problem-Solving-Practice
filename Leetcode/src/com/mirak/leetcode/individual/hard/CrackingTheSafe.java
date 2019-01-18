package com.mirak.leetcode.individual.hard;

import java.util.*;

public class CrackingTheSafe {

  public String crackSafe(int n, int k) {
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
}
