package com.mirak.leetcode.contests.contest119;

import java.util.*;

public class LargestPerimeterTriangle {

  public int largestPerimeter(int[] A) {
    for (int i = A.length - 3; i >= 0; i--) {
      Arrays.sort(A);
      if (A[i] + A[i + 1] > A[i + 2]) {
        return A[i] + A[i + 1] + A[i + 2];
      }
    }
    return 0;
  }
}
