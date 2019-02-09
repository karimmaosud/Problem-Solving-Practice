package com.mirak.leetcode.individual.hard;

import java.util.*;

public class FindTheShortestSuperstring {

  public String shortestSuperstring(String[] A) {
    int n = A.length;
    int[][] weight = new int[n + 1][n + 1];
    initWeight(A, weight);

    int[][] dp = new int[n + 1][1 << (n + 1)];
    int[][] path = new int[n + 1][1 << (n + 1)];
    for (int i = 0; i < dp.length; i++) {
      Arrays.fill(dp[i], -1);
    }
    solve(0, 1, n, weight, A, dp, path);
    StringBuilder builder = new StringBuilder();
    getConcatenatedStringFromPath(0, 1, n, path, builder, weight, A);
    return builder.toString();
  }

  private void initWeight(String[] A, int[][] weight) {
    for (int i = 0; i < A.length; i++) {
      for (int j = 0; j < A.length; j++) {
        if (i == j) {
          continue;
        }
        weight[j + 1][i + 1] = getSharedSuffix(A[i], A[j]);
      }
    }
  }

  private int getSharedSuffix(String a, String b) {
    String s = a + "$" + b;
    int[] phi = new int[s.length()];
    for (int i = 1; i < s.length(); i++) {
      int j = phi[i - 1];
      while (j > 0 && s.charAt(j) != s.charAt(i)) {
        j = phi[j - 1];
      }
      if (s.charAt(j) == s.charAt(i)) {
        phi[i] = ++j;
      }
    }
    return phi[s.length() - 1];
  }

  private int solve(int index, int mask, int n, int[][] weight, String[] A, int[][] dp,
      int[][] path) {
    if (mask == ((1 << (n + 1)) - 1)) {
      return 0;
    }

    if (dp[index][mask] != -1) {
      return dp[index][mask];
    }

    int ans = Integer.MAX_VALUE;
    for (int i = 0; i <= n; i++) {
      if ((mask & (1 << i)) == 0) {
        int length =
            A[i - 1].length() - weight[index][i] + solve(i, mask | (1 << i), n, weight, A, dp,
                path);
        if (length < ans) {
          ans = length;
          path[index][mask] = i;
        }
      }
    }
    return dp[index][mask] = ans;
  }

  private void getConcatenatedStringFromPath(int index, int mask, int n, int[][] path,
      StringBuilder builder, int[][] weight, String[] A) {
    if (mask == (1 << (n + 1)) - 1) {
      return;
    }

    int next = path[index][mask];
    builder.append(A[next - 1].substring(weight[index][next]));
    getConcatenatedStringFromPath(next, mask | (1 << next), n, path, builder, weight, A);
  }
}
