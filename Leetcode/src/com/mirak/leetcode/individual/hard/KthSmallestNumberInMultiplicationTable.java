package com.mirak.leetcode.individual.hard;

public class KthSmallestNumberInMultiplicationTable {

  public int findKthNumber(int m, int n, int k) {
    int low = 1;
    int high = n * m;
    while (low <= high) {
      int mid = low + (high - low) / 2;
      int smallerCount = getSmallerCount(n, m, mid);
      if (smallerCount < k) {
        low = mid + 1;
      } else {
        high = mid - 1;
      }
    }
    return low;
  }

  private int getSmallerCount(int n, int m, int k) {
    int count = 0;
    int row = 1;
    int col = m;
    while (row <= n && col >= 1) {
      if (row * col <= k) {
        count += col;
        row++;
      } else {
        col--;
      }
    }
    return count;
  }
}
