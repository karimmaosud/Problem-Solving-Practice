package com.mirak.leetcode.contests.contest112;

import java.util.*;

public class MinimumIncrementToMakeArrayUnique {

  public int minIncrementForUnique(int[] A) {
    Arrays.sort(A);
    int max = 0;
    int res = 0;
    for (int i = 0; i < A.length; i++) {
      if (A[i] < max) {
        res += (max - A[i]);
      }
      max = Math.max(max + 1, A[i] + 1);
    }
    return res;
  }
}
