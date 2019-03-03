package com.mirak.leetcode.contests.contest126;

public class MaxConsecutiveOnesIII {

  public static int longestOnes(int[] A, int K) {
    int n = A.length;
    if (n == 0) {
      return 0;
    }
    int left = 0, right = 0, ans = 0;
    int windowK = (A[0] == 0 ? 1 : 0);
    while (right < n) {
      if (windowK > K) {
        windowK -= (A[left++] == 0 ? 1 : 0);
      } else {
        ans = Math.max(ans, right - left + 1);
        right++;
        if (right < n) {
          windowK += (A[right] == 0 ? 1 : 0);
        }
      }
    }
    return ans;
  }
}
