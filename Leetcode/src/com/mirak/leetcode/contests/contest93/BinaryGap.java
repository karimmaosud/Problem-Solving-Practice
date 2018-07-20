package com.mirak.leetcode.contests.contest93;

public class BinaryGap {
  public int binaryGap(int N) {
    int index = -1;
    int dist = 0;
    int runnerIndex = 0;
    while(N != 0) {
      if((N & 1) != 0) {
        if(index != -1) {
          dist = Math.max(dist, runnerIndex - index);
        }
        index = runnerIndex;
      }
      N >>= 1;
      runnerIndex++;
    }
    return dist;
  }
}
