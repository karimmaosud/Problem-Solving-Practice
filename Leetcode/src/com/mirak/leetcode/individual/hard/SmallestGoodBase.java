package com.mirak.leetcode.individual.hard;

public class SmallestGoodBase {

  private final long INF = 1000000000000000000L;

  public String smallestGoodBase(String n) {
    long min = INF;
    long num = Long.parseLong(n);
    for (int len = 2; len <= 60; ++len) {
      long base = smallestBaseWithLengthN(num, len);
      if (getNumWithBaseAndLen(num, base, len) == num) {
        min = Math.min(min, base);
      }
    }

    return "" + min;
  }

  private long smallestBaseWithLengthN(long num, int len) {
    long low = 2;
    long high = INF;
    while (low <= high) {
      long mid = low + (high - low) / 2;
      long writtenNum = getNumWithBaseAndLen(num, mid, len);
      if (writtenNum >= num) {
        high = mid - 1;
      } else {
        low = mid + 1;
      }
    }
    return low;
  }

  private long getNumWithBaseAndLen(long num, long base, int len) {
    int maxPow = Math.min((int) (Math.log10(num) / Math.log10(base)), len - 1);
    long ans = 0;
    long b = 1;
    for (int i = 0; i <= maxPow; i++) {
      ans += b;
      b *= base;
    }
    if (ans < num) {
      return maxPow == len - 1 ? ans : num + 1;
    }
    return ans;
  }

}
