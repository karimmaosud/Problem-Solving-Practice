package com.mirak.leetcode.contests.contest104;

public class PartitionArrayIntoDisjointIntervals {
  public int partitionDisjoint(int[] A) {

    if(A.length == 0) {
      return 0;
    }

    int[] leftToRightMax = new int[A.length];
    int[] rightToLeftMin = new int[A.length];
    leftToRightMax[0] = A[0];
    rightToLeftMin[A.length - 1] = A[A.length - 1];
    for(int i = 1; i < A.length; i++) {
      leftToRightMax[i] = Math.max(leftToRightMax[i - 1], A[i]);
    }
    for(int i = A.length - 2; i >= 0; i--) {
      rightToLeftMin[i] = Math.min(rightToLeftMin[i + 1], A[i]);
    }
    for(int i = 0; i < A.length - 1; i++) {
      if(leftToRightMax[i] <= rightToLeftMin[i + 1]) {
        return i + 1;
      }
    }
    return 0;
  }
}
