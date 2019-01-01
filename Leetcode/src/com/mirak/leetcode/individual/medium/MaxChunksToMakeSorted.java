package com.mirak.leetcode.individual.medium;

public class MaxChunksToMakeSorted {
  public int maxChunksToSorted(int[] arr) {
    int mask = 0;
    int res = 0;
    for (int i = 0; i < arr.length; i++) {
      mask |= (1 << arr[i]);
      if(mask == (1 << (i + 1)) - 1) {
        res++;
      }
    }
    return res;
  }
}
