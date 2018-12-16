package com.mirak.leetcode.contests.contest114;

public class DeleteColumnsToMakeSortedII {

  public int minDeletionSize(String[] A) {

    if (A.length == 0 || A[0].length() == 0) {
      return 0;
    }

    boolean[][] lessThan = new boolean[A.length][A.length];
    int minDelete = 0;
    for (int j = 0; j < A[0].length(); j++) {
      boolean mustDelete = false;
      for (int i = 0; i < A.length - 1; i++) {
        if (A[i].charAt(j) > A[i + 1].charAt(j) && !lessThan[i][i + 1]) {
          mustDelete = true;
          break;
        }
      }
      if (mustDelete) {
        minDelete++;
        continue;
      }

      for (int i = 0; i < A.length; i++) {
        for (int k = i + 1; k < A.length; k++) {
          if (A[i].charAt(j) < A[k].charAt(j)) {
            lessThan[i][k] = true;
          }
        }
      }
    }
    return minDelete;
  }
}
