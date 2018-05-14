package com.mirak.google.codejam.cg2018.round1C;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class AntStackSmall {
  public static void main(String[] args) throws IOException {

    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    int T = Integer.parseInt(reader.readLine());
    String[] strs;
    for(int t = 1; t <= T; t++) {
      int n = Integer.parseInt(reader.readLine());
      strs = reader.readLine().split(" ");
      long[] weight = new long[n];
      for(int i = n - 1; i >= 0; i--) {
        weight[i] = Long.parseLong(strs[n - i - 1]);
      }

      long[][] dp = new long[2][n + 1];
      for(int i = 0; i < dp.length; i++) {
        dp[i][0] = 0;
        for(int j = 1; j < n + 1; j++) {
          dp[i][j] = Long.MAX_VALUE;
        }
      }
      for(int i = n - 1; i >= 0; i--){
        for(int j = n - i; j >= 1; j--) {
          dp[i%2][j] = dp[(i + 1)%2][j];
          if(dp[(i + 1) % 2][j - 1] <= 6 * weight[i]) {
            dp[i%2][j] = Math.min(dp[i%2][j], weight[i] + dp[(i + 1)%2][j - 1]);
          }
        }
      }
      int maxLen = 0;
      for(int j = n; j >= 1; j--) {
        if(dp[0][j] != Long.MAX_VALUE || dp[1][j] != Long.MAX_VALUE){
          maxLen = j;
          break;
        }
      }
      System.out.println("Case #" + t + ": " + maxLen);
    }
  }
}
