package com.mirak.leetcode.individual.hard;

import java.util.*;

public class PalindromePartitioningII {

  private final int INF = 1000000000;

  public int minCut(String s) {
    int n = s.length();
    if (n == 0) {
      return 0;
    }
    int[] dOdd = new int[n];
    int[] dEven = new int[n];
    runManacher(s, dOdd, 0);
    runManacher(s, dEven, 1);

    int[] dp = new int[n + 1];
    Arrays.fill(dp, INF);
    dp[n] = -1;

    for (int i = n - 1; i >= 0; --i) {
      for (int j = i; j < n; ++j) {
        if (isPalindrome(i, j, ((j - i + 1) & 1) == 1 ? dOdd : dEven)) {
          dp[i] = Math.min(dp[i], 1 + dp[j + 1]);
        }
      }
    }
    return dp[0];
  }

  private void runManacher(String s, int[] d, int even) {
    int l = 0, r = -1;
    int n = s.length();
    for (int i = 0; i < n; ++i) {
      int k = i > r ? 1 ^ even : Math.min(d[l + r - i + even], r - i);
      while (i - k - even >= 0 && i + k < n && s.charAt(i - k - even) == s.charAt(i + k)) {
        k++;
      }
      d[i] = k--;
      if (i + k > r) {
        r = i + k;
        l = i - k - even;
      }
    }
  }

  private boolean isPalindrome(int i, int j, int[] d) {
    int len = j - i + 1;
    int idx = (i + j) / 2 + ((len & 1) == 0 ? 1 : 0);
    int palindromeLength = 2 * d[idx];
    if ((len & 1) == 1) {
      palindromeLength--;
    }
    return palindromeLength >= len;
  }
}
