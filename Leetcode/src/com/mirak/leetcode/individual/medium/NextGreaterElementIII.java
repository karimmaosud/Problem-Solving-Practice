package com.mirak.leetcode.individual.medium;

public class NextGreaterElementIII {

  public int nextGreaterElement(int n) {

    int[] count = new int[10];
    while (n != 0) {
      int d = n % 10;
      count[d]++;
      n /= 10;
      int nextGreaterDigit = getNextGreaterDigit(d, count);

      if (nextGreaterDigit == -1) {
        continue;
      }

      long next = (long) n * 10 + nextGreaterDigit;
      count[nextGreaterDigit]--;
      next = appendAllDigits(next, count);
      if (next <= (1L << 31) - 1) {
        return (int) next;
      }
      return -1;
    }
    return -1;
  }

  private int getNextGreaterDigit(int d, int[] count) {
    for (int i = d + 1; i < 10; i++) {
      if (count[i] > 0) {
        return i;
      }
    }
    return -1;
  }

  private long appendAllDigits(long next, int[] count) {
    for (int i = 0; i < 10; i++) {
      while (count[i] > 0) {
        next = next * 10 + i;
        count[i]--;
      }
    }
    return next;
  }
}
