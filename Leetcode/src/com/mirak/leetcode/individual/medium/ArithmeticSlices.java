package com.mirak.leetcode.individual.medium;

public class ArithmeticSlices {

  public int numberOfArithmeticSlices(int[] A) {

    if (A.length < 3) {
      return 0;
    }

    int count = 0;

    int l = 0;

    while (l + 2 < A.length) {
      if (A[l + 1] - A[l] != A[l + 2] - A[l + 1]) {
        l++;
        continue;
      }
      int diff = A[l + 1] - A[l];
      int r = l + 3;
      while (r < A.length && A[r] - A[r - 1] == diff) {
        r++;
      }
      r--;
      int slice = r - l - 1;
      count += (slice * (slice + 1)) / 2;
      l = r + 1;
    }
    return count;
  }
}
