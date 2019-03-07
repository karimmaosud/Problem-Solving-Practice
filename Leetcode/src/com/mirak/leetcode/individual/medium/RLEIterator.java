package com.mirak.leetcode.individual.medium;

public class RLEIterator {

  private int idx;
  private int[] A;
  private long skipped;

  public RLEIterator(int[] A) {
    this.A = A;
    this.idx = 0;
    this.skipped = 0;
  }

  public int next(int n) {
    long newSkipped = skipped + n;
    while (idx < A.length && skipped + A[idx] < newSkipped) {
      skipped += A[idx];
      idx += 2;
    }
    if (idx == A.length) {
      return -1;
    }
    A[idx] = (int) (skipped + A[idx] - newSkipped);
    return A[idx + 1];
  }

}
