package com.mirak.leetcode.individual.medium;

public class H_IndexII {

  public int hIndex(int[] citations) {
    int n = citations.length;

    int low = 1;
    int high = n;

    while (low <= high) {
      int mid = low + (high - low) / 2;
      if (citations[n - mid] < mid) {
        high = mid - 1;
      } else {
        low = mid + 1;
      }
    }
    return high;
  }

}
