package com.mirak.leetcode.individual.medium;

public class ImageOverlap {

  public int largestOverlap(int[][] A, int[][] B) {
    int n = A.length;
    if (n == 0) {
      return 0;
    }
    int max = 0;
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < n; j++) {
        // (0,0) → (i,j)
        max = Math.max(max, getCount(A, B, 0, 0, i, j, n - 1 - i, n - 1 - j));
        // (0,j) → (i, n-1)
        max = Math.max(max, getCount(A, B, 0, j, i, n - 1, n - 1 - i, 0));
        // (i,0) → (n-1,j)
        max = Math.max(max, getCount(A, B, i, 0, n - 1, j, 0, n - 1 - j));
        // (i,j) → (n-1,n-1)
        max = Math.max(max, getCount(A, B, i, j, n - 1, n - 1, 0, 0));
      }
    }
    return max;
  }

  private int getCount(int[][] A, int[][] B, int ia1, int ja1, int ia2, int ja2, int ib1, int jb1) {
    int intersection = 0;
    for (; ia1 <= ia2; ia1++, ib1++) {
      for (int ja = ja1, jb = jb1; ja <= ja2; ja++, jb++) {
        if (A[ia1][ja] == B[ib1][jb]) {
          intersection += A[ia1][ja];
        }
      }
    }
    return intersection;
  }
}

