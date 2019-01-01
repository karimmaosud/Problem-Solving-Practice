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

class ImageOverlap2 {

  public int largestOverlap(int[][] A, int[][] B) {
    int n = A.length;
    int maxIntersection = 0;
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < n; j++) {
        maxIntersection = Math
            .max(maxIntersection, getIntersection(A, B, 0, 0, n - i - 1, n - j - 1, i + 1, j + 1));
        maxIntersection = Math
            .max(maxIntersection, getIntersection(A, B, 0, j, n - i - 1, 0, i + 1, n - j));
        maxIntersection = Math
            .max(maxIntersection, getIntersection(A, B, i, 0, 0, n - j - 1, n - i, j + 1));
        maxIntersection = Math
            .max(maxIntersection, getIntersection(A, B, i, j, 0, 0, n - i, n - j));
      }
    }
    return maxIntersection;
  }

  private int getIntersection(int[][] A, int[][] B, int ia, int ja, int ib, int jb, int walkRows,
      int walkColumns) {
    int res = 0;
    for (int rowOffset = 0; rowOffset < walkRows; rowOffset++) {
      for (int columnOffset = 0; columnOffset < walkColumns; columnOffset++) {
        res += A[ia + rowOffset][ja + columnOffset] & B[ib + rowOffset][jb + columnOffset];
      }
    }
    return res;
  }
}

