package com.mirak.leetcode.individual.hard;

import java.util.*;

public class OptimalAccountBalancing {

  public int minTransfers(int[][] transactions) {
    Map<Integer, Integer> map = new HashMap<>();
    int idx = 0;
    for (int[] transaction : transactions) {
      int u = transaction[0];
      int v = transaction[1];
      if (!map.containsKey(u)) {
        map.put(u, idx++);
      }
      if (!map.containsKey(v)) {
        map.put(v, idx++);
      }
    }
    int[] debits = new int[idx];
    for (int[] transaction : transactions) {
      int u = map.get(transaction[0]);
      int v = map.get(transaction[1]);
      int c = transaction[2];
      debits[u] += c;
      debits[v] -= c;
    }
    Arrays.sort(debits);
    return dfs(debits);
  }

  private int dfs(int[] debits) {

    int start = 0;
    while (start < debits.length && debits[start] == 0) {
      start++;
    }

    if (start == debits.length) {
      return 0;
    }

    int ans = Integer.MAX_VALUE;
    int savedStart = debits[start];
    for (int j = start + 1; j < debits.length; j++) {
      if (debits[j] <= 0) {
        continue;
      }
      debits[start] = debits[j] + savedStart < 0 ? debits[j] + savedStart : 0;
      int savedJ = debits[j];
      debits[j] = Math.max(0, debits[j] + savedStart);
      ans = Math.min(ans, 1 + dfs(debits));
      debits[j] = savedJ;
    }
    debits[start] = savedStart;
    return ans;
  }

}
