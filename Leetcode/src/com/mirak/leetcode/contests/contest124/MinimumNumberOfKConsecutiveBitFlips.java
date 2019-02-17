package com.mirak.leetcode.contests.contest124;

public class MinimumNumberOfKConsecutiveBitFlips {
  public int minKBitFlips(int[] A, int K) {
    int ans = 0;
    int[] inc = new int[A.length + 1];
    for (int i = 0; i + K <= A.length; i++) {
      inc[i] += (i - 1 >= 0 ? inc[i - 1] : 0);

      if ((A[i] ^ (inc[i] & 1)) == 1) {
        continue;
      }
      ans++;
      inc[i]++;
      inc[i + K]--;
    }

    for (int i = A.length - K + 1; i < A.length; i++) {
      inc[i] += inc[i - 1];
      if ((A[i] ^ (inc[i] & 1)) == 0) {
        return -1;
      }
    }
    return ans;
  }

}
