package com.mirak.leetcode.contests.contest119;

public class SubarraySumsDivisibleByK {

  public int subarraysDivByK(int[] A, int K) {
    int[] count = new int[K];
    int res = 0;
    int sum = 0;
    for (int num : A) {
      sum += num;
      int setKey = ((sum % K) + K) % K;
      if (setKey == 0) {
        res++;
      }
      res += count[setKey];
      count[setKey]++;
    }
    return res;
  }
}
