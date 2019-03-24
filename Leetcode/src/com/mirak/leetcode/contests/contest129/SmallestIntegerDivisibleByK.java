package com.mirak.leetcode.contests.contest129;

public class SmallestIntegerDivisibleByK {

  public int smallestRepunitDivByK(int K) {
    boolean[] vis = new boolean[K + 1];
    int prev = 1;
    int len = 1;
    for (int i = 0; i <= K; i++) {
      if (prev % K == 0) {
        return len;
      }
      if (vis[prev % K]) {
        return -1;
      }
      vis[prev % K] = true;
      prev = (prev * 10 + 1) % K;
      len++;
    }
    return -1;
  }
}
