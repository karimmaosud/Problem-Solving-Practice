package com.mirak.leetcode.individual.medium;

public class LongestMountainInArray {
  public int longestMountain(int[] A) {
    if(A.length == 0) {
      return 0;
    }

    int res = 0;
    int mountainStart = getMountainStart(A, 0);
    while(mountainStart != -1) {
      // A[mountainStart] < A[mountainStart + 1]
      int j = mountainStart + 1;
      while(j < A.length && A[j] > A[j - 1]) {
        j++;
      }
      if(j == A.length) {
        // not a valid peak.
        break;
      }
      // A[j] <= A[j - 1]
      if(A[j] == A[j - 1]) {
        // not a valid peak.
        mountainStart = getMountainStart(A, j);
        continue;
      }
      // move j back to mountain peak
      j--;
      while(j + 1 < A.length && A[j] > A[j + 1]) {
        j++;
      }
      res = Math.max(res, j - mountainStart + 1);
      mountainStart = getMountainStart(A, j);
    }
    return res;
  }

  private int getMountainStart(int[] A, int index) {
    for(int i = index; i < A.length - 1; i++) {
      if(A[i] < A[i + 1]) {
        return i;
      }
    }
    return -1;
  }
}
