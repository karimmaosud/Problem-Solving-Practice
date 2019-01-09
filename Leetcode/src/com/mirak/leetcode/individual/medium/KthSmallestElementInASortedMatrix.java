package com.mirak.leetcode.individual.medium;

public class KthSmallestElementInASortedMatrix {

  public int kthSmallest(int[][] matrix, int k) {
    int n = matrix.length;
    int low = matrix[0][0];
    int high = matrix[n - 1][n - 1];
    while (low <= high) {
      int mid = low + (high - low) / 2;
      int count = 0;
      for (int i = 0; i < n; i++) {
        int idx = search(mid, matrix[i]);
        if (idx == -1) {
          continue;
        }
        count += (idx + 1);
      }
      if (count < k) {
        low = mid + 1;
      } else {
        high = mid - 1;
      }
    }
    return high + 1;
  }

  private int search(int key, int[] row) {
    int low = 0;
    int high = row.length - 1;
    while (low <= high) {
      int mid = (low + high) / 2;
      if (row[mid] <= key) {
        low = mid + 1;
      } else {
        high = mid - 1;
      }
    }
    return high;
  }
}
