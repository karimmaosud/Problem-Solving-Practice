package com.mirak.leetcode.contests.contest127;

import java.util.*;

public class MaximizeSumOfArrayAfterKNegations {

  public int largestSumAfterKNegations(int[] A, int K) {
    Arrays.sort(A);
    for (int i = 0; i < A.length; i++) {
      if (A[i] < 0) {
        A[i] *= -1;
        K--;
      }
      if (K == 0) {
        break;
      }
    }
    if (K == 0 || (K & 1) == 0) {
      return findSum(A);
    }

    A[minIndex(A)] *= -1;
    return findSum(A);
  }

  private int minIndex(int[] A) {
    int idx = -1;
    int min = Integer.MAX_VALUE;
    for (int i = 0; i < A.length; i++) {
      if (A[i] < min) {
        min = A[i];
        idx = i;
      }
    }
    return idx;
  }

  private int findSum(int[] A) {
    int sum = 0;
    for (int i = 0; i < A.length; i++) {
      sum += A[i];
    }
    return sum;
  }
}
