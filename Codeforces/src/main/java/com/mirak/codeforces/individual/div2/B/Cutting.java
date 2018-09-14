package com.mirak.codeforces.individual.div2.B;

import java.io.*;
import java.util.*;

public class Cutting {

  public static void main(String[] args) throws IOException {
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    String[] strs = reader.readLine().split(" ");

    int n = Integer.parseInt(strs[0]);
    int b = Integer.parseInt(strs[1]);

    strs = reader.readLine().split(" ");
    int[] nums = new int[n];
    for (int i = 0; i < n; i++) {
      nums[i] = Integer.parseInt(strs[i]);
    }

    int[][][] dp = new int[101][101][101];
    initDp(dp);
    System.out.println(Math.max(0, solve(0, 0, b, nums, dp)));
  }

  private static void initDp(int[][][] dp) {
    for (int i = 0; i < dp.length; i++) {
      for (int j = 0; j < dp[i].length; j++) {
        Arrays.fill(dp[i][j], -1);
      }
    }
  }

  private static int solve(int odd, int even, int rem, int[] nums, int[][][] dp) {

    if (rem < 0) {
      return Integer.MIN_VALUE;
    }

    if (odd + even == nums.length) {
      return odd == even ? 0 : Integer.MIN_VALUE;
    }

    if (dp[odd][even][rem] != -1) {
      return dp[odd][even][rem];
    }

    int oddNumber = nums[odd + even] & 1;
    int nextOdd = odd + oddNumber;
    int nextEven = even + (oddNumber ^ 1);

    int ans = solve(nextOdd, nextEven, rem, nums, dp);

    if (nextOdd == nextEven && nextEven + nextOdd != nums.length) {
      // possible cut.
      if (odd + even + 1 < nums.length) {
        ans = Math.max(ans, 1 + solve(nextOdd, nextEven,
            rem - Math.abs(nums[odd + even] - nums[nextEven + nextEven]), nums, dp));
      }
    }
    return dp[odd][even][rem] = ans;
  }
}
