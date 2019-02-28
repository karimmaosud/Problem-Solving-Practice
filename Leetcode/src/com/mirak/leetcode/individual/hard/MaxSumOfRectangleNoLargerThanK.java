package com.mirak.leetcode.individual.hard;

import java.util.*;

public class MaxSumOfRectangleNoLargerThanK {

  public int maxSumSubmatrix2(int[][] matrix, int k) {
    int n = matrix.length;
    if (n == 0) {
      return 0;
    }

    int m = matrix[0].length;
    int[][][] sum = new int[m][n][m];

    TreeSet<Integer>[][] sortedColumns = new TreeSet[m][m];
    for (int i = 0; i < sortedColumns.length; i++) {
      for (int j = 0; j < sortedColumns[i].length; j++) {
        sortedColumns[i][j] = new TreeSet<>();
      }
    }
    int ans = Integer.MIN_VALUE;
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < m; j++) {
        for (int l = 0; l <= j; l++) {
          sum[l][i][j] += matrix[i][j];
          if (i - 1 >= 0) {
            sum[l][i][j] += sum[l][i - 1][j];
          }
          if (j - 1 >= 0) {
            sum[l][i][j] += sum[l][i][j - 1];
          }
          if (i - 1 >= 0 && j - 1 >= 0) {
            sum[l][i][j] -= sum[l][i - 1][j - 1];
          }
        }
        for (int l = 0; l <= j; l++) {
          int current = sum[l][i][j];
          if (current <= k) {
            ans = Math.max(ans, current);
          }
          Integer ceil = sortedColumns[l][j].ceiling(current - k);
          if (ceil != null && current - ceil <= k) {
            ans = Math.max(ans, current - ceil);
          }
        }
        for (int l = 0; l <= j; l++) {
          sortedColumns[l][j].add(sum[l][i][j]);
        }
      }
    }
    return ans;
  }

  public int maxSumSubmatrix(int[][] matrix, int k) {
    int n = matrix.length;
    if (n == 0) {
      return 0;
    }
    int m = matrix[0].length;
    int ans = Integer.MIN_VALUE;
    for (int col = 0; col < m; col++) {
      int[] sum = new int[n];
      for (int j = col; j < m; j++) {
        for (int i = 0; i < n; i++) {
          sum[i] += matrix[i][j];
        }
        TreeSet<Integer> set = new TreeSet<>();
        int rectangleSum = 0;
        for (int i = 0; i < n; i++) {
          rectangleSum += sum[i];
          if (rectangleSum <= k) {
            ans = Math.max(ans, rectangleSum);
          }
          Integer sub = set.ceiling(rectangleSum - k);
          if (sub != null && rectangleSum - sub <= k) {
            ans = Math.max(ans, rectangleSum - sub);
          }
          set.add(rectangleSum);
        }
      }
    }
    return ans;
  }
}
