package com.mirak.leetcode.individual.hard;

public class KthSmallestInLexicographicalOrder {

  public int findKthNumber(int n, int k) {
    return findKthNumber(n, k, 0);
  }

  private int findKthNumber(int n, int k, int num) {
    if (k == 0) {
      return num;
    }
    int d = num == 0 ? 1 : 0;
    for (; d < 10; d++) {
      int skipped = getSkippedNumbers(n, num, d);
      if (k - skipped > 0) {
        k -= skipped;
      } else {
        break;
      }
    }
    return findKthNumber(n, k - 1, num * 10 + d);
  }

  private int getSkippedNumbers(int n, int prefix, int d) {
    int skipped = 0;
    long start = prefix * 10 + d;
    long pow = 1;

    for (int i = 0; i < 10; i++) {
      if (start > n) {
        break;
      }
      long end = Math.min(n, start + pow - 1);
      skipped += end - start + 1;
      start *= 10;
      pow *= 10;
    }
    return skipped;
  }
}