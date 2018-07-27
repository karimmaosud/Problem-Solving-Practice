package com.mirak.leetcode.contests.contest94;

import java.util.HashMap;
import java.util.Map;

public class LengthOfLongestFibonacciSubsequence {
  public static int lenLongestFibSubseq(int[] A) {
    int longestLength = 0;

    boolean[][] vis = new boolean[A.length][A.length];
    Map<Integer, Integer> map = new HashMap<>();

    for(int i = 0; i < A.length; i++) {
      map.put(A[i], i);
    }

    for(int i = 0; i < A.length; i++) {
      for(int j = i + 1; j < A.length; j++) {
        if(vis[i][j]) {
          continue;
        }
        // starting of a new sequence.
        int l = i;
        int k = j;
        int len = 2;
        vis[l][k] = true;
        while(k < A.length && map.containsKey(A[l] + A[k])) {
          len++;
          int nextIndex = map.get(A[l] + A[k]);
          l = k;
          k = nextIndex;
          vis[l][k] = true;
        }
        longestLength = Math.max(longestLength, len);
      }
    }
    if (longestLength == 2) {
      return 0;
    }
    return longestLength;
  }

  public static void main(String[] args) {
    int[] nums = {1,3,7,11,12,14,18};
    System.out.println(lenLongestFibSubseq(nums));
  }
}
