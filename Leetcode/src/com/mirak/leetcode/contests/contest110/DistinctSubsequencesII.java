package com.mirak.leetcode.contests.contest110;

import java.util.Arrays;

public class DistinctSubsequencesII {

  private long mod = 1000000007;

  public int distinctSubseqII(String S) {
    if (S.length() == 0) {
      return 0;
    }

    long[] count = new long[S.length()];
    int[] lastOccurrence = new int[26];
    Arrays.fill(lastOccurrence, -1);
    count[0] = 1;
    lastOccurrence[S.charAt(0) - 'a'] = 0;
    for (int i = 1; i < S.length(); i++) {
      count[i] = (count[i - 1] * 2) % mod;
      if (lastOccurrence[S.charAt(i) - 'a'] == -1) {
        count[i] = (count[i] + 1) % mod;
      } else if (lastOccurrence[S.charAt(i) - 'a'] > 0) {
        count[i] = (count[i] - count[lastOccurrence[S.charAt(i) - 'a'] - 1] + mod) % mod;
      }
      lastOccurrence[S.charAt(i) - 'a'] = i;
    }
    return (int) count[S.length() - 1];
  }
}
