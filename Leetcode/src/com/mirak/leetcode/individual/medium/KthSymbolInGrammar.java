package com.mirak.leetcode.individual.medium;

public class KthSymbolInGrammar {
  public int kthGrammar(int N, int K) {
    int len = 1 << (N - 1);
    int res = 0;
    while (len > 1) {
      int mid = len / 2;
      if(K > mid) {
        res ^= 1;
        K -= mid;
      }
      len >>= 1;
    }
    return res;
  }
}

