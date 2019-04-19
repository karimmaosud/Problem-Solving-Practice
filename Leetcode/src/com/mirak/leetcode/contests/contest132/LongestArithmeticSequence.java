package com.mirak.leetcode.contests.contest132;

import java.util.*;

public class LongestArithmeticSequence {

  public int longestArithSeqLength(int[] A) {
    int n = A.length;
    Map<Integer, Integer>[] diffCount = new HashMap[n];
    for (int i = 0; i < n; ++i) {
      diffCount[i] = new HashMap<>();
    }
    int ans = 1;
    for (int i = 1; i < n; ++i) {
      for (int j = 0; j < i; ++j) {
        int diff = A[i] - A[j];
        diffCount[i].putIfAbsent(diff, 2);
        int len = diffCount[j].getOrDefault(diff, 0);
        diffCount[i].put(diff, Math.max(diffCount[i].get(diff), len + 1));
        ans = Math.max(ans, diffCount[i].get(diff));
      }
    }
    return ans;
  }
}
