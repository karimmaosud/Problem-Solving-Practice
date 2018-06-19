package com.mirak.leetcode.contests.contest89;

public class PeakIndexInAMountainArray {
  public int peakIndexInMountainArray(int[] A) {
    for(int i = 1; i < A.length - 1; i++) {
      if(A[i] > A[i - 1] && A[i] > A[i + 1]) {
        return i;
      }
    }
    return -1;
  }
}
