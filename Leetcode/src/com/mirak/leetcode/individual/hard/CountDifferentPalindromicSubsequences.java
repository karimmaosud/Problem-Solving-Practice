package com.mirak.leetcode.individual.hard;

public class CountDifferentPalindromicSubsequences {

  private final int MOD = 1000000007;

  public int countPalindromicSubsequences(String S) {
    int n = S.length();
    int[][][] dp = new int[4][n][n];

    for (int i = 0; i < n; i++) {

      int ci = S.charAt(i) - 'a';

      dp[ci][i][i] = 1;

      for (int j = i - 1; j >= 0; j--) {
        int cj = S.charAt(j) - 'a';
        for (int ck = 0; ck < 4; ck++) {
          if (ck == ci && ck == cj) {
            for (int x = 0; x < 4; x++) {
              dp[ck][j][i] = (dp[ck][j][i] + dp[x][j + 1][i - 1]) % MOD;
            }
            dp[ck][j][i] = (dp[ck][j][i] + 2) % MOD;
          } else {
            dp[ck][j][i] =
                (dp[ck][j][i] + dp[ck][(cj != ck ? j + 1 : j)][(ci != ck ? i - 1 : i)]) % MOD;
          }
        }
      }
    }
    
    int ans = 0;
    for (int i = 0; i < 4; i++) {
      ans = (dp[i][0][n - 1] + ans) % MOD;
    }
    return ans;
  }
}
