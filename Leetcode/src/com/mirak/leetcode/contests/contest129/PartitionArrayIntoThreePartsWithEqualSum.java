package com.mirak.leetcode.contests.contest129;

import java.util.*;

public class PartitionArrayIntoThreePartsWithEqualSum {

  public static boolean canThreePartsEqualSum(int[] A) {
    int n = A.length;
    if (n < 3) {
      return false;
    }
    long[] leftSum = getSumArray(A, 0, 1);
    long[] rightSum = getSumArray(A, n - 1, -1);
    Set<Long> vis = new HashSet<>();
    vis.add(leftSum[0]);
    for (int i = 1; i < n - 1; i++) {
      long arraySum = rightSum[i + 1];
      if (vis.contains(leftSum[i] - arraySum) && leftSum[i] - arraySum == arraySum) {
        return true;
      }
      vis.add(leftSum[i]);
    }
    return false;
  }


  private static long[] getSumArray(int[] A, int start, int inc) {
    long[] sum = new long[A.length];
    for (int i = start; i >= 0 && i < A.length; i += inc) {
      sum[i] = A[i];
      if (i - inc >= 0 && i - inc < A.length) {
        sum[i] += sum[i - inc];
      }
    }
    return sum;
  }

  public static void main(String[] args) {
    System.out.println(canThreePartsEqualSum(new int[]{3,3,6,5,-2,2,5,1,-9,4}));
  }

}
