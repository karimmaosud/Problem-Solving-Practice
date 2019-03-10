package com.mirak.leetcode.contests.contest127;

public class MinimumDominoRotationsForEqualRow {

  public int minDominoRotations(int[] A, int[] B) {
    int ans = Integer.MAX_VALUE;
    for (int i = 1; i <= 6; i++) {
      ans = Math.min(ans,
          Math.min(minRotationsToValue(A, B, i, true), minRotationsToValue(A, B, i, false)));
    }
    return ans == Integer.MAX_VALUE ? -1 : ans;
  }

  private int minRotationsToValue(int[] A, int[] B, int value, boolean up) {
    int rotations = 0;

    for (int i = 0; i < A.length; i++) {
      if (A[i] != value && B[i] != value) {
        return Integer.MAX_VALUE;
      }

      if ((A[i] != value && up) || (B[i] != value && !up)) {
        rotations++;
      }
    }
    return rotations;
  }

}
