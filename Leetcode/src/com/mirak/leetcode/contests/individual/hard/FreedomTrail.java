package com.mirak.leetcode.contests.individual.hard;

import java.util.ArrayList;

public class FreedomTrail {
  public int findRotateSteps(String ring, String key) {
    ArrayList<Integer>[] indexes = new ArrayList[26];
    int[][] dp = new int[101][101];
    initIndexes(ring, indexes);
    initDp(dp);
    return solve(key, ring.length(), 0, 0, indexes, dp);
  }

  private void initIndexes(String ring, ArrayList<Integer>[] indexes){
    for(int i = 0; i < indexes.length; i++) {
      indexes[i] = new ArrayList<>();
    }
    for(int i = 0; i < ring.length(); i++) {
      indexes[ring.charAt(i) - 'a'].add(i);
    }
  }

  private void initDp(int[][] dp){
    for(int i = 0; i < dp.length; i++) {
      for(int j = 0; j < dp[i].length; j++) {
        dp[i][j] = -1;
      }
    }
  }

  private int solve(String key, int ringLength, int cursor, int index, ArrayList<Integer>[] indexes, int[][] dp) {
    if(index == key.length()) {
      return 0;
    }
    if(dp[cursor][index] != -1) {
      return dp[cursor][index];
    }
    int ans = Integer.MAX_VALUE;
    for(int i: indexes[key.charAt(index) - 'a']) {
      ans = Math.min(ans, 1 + Math.min(Math.abs(i - cursor), ringLength - Math.abs(i - cursor)) + solve(key, ringLength, i, index + 1, indexes, dp));
    }
    dp[cursor][index] = ans;
    return ans;
  }

  private int findRotateStepsIterative(String ring, String key) {
    ArrayList<Integer>[] indexes = new ArrayList[26];
    initIndexes(ring, indexes);
    int[][] dp = new int[key.length() + 1][ring.length()];
    for(int i = 0; i < dp.length; i++) {
      for(int j = 0; j < dp[i].length; j++) {
        dp[i][j] = Integer.MAX_VALUE;
      }
    }
    // cursor is at index 0 of ring initially.
    dp[0][0] = 0;

    ArrayList<Integer> dummy = new ArrayList<>();
    dummy.add(0);

    for(int i = 0; i < key.length(); i++) {
      ArrayList<Integer> prevIndexes = i == 0 ? dummy : indexes[key.charAt(i - 1) - 'a'];
      for(int prevIndex: prevIndexes) {
        for(int curIndex: indexes[key.charAt(i) - 'a']) {
          int diff = Math.abs(prevIndex - curIndex);
          int steps = Math.min(diff, ring.length() - diff);
          dp[i + 1][curIndex] = Math.min(dp[i + 1][curIndex], 1 + steps + dp[i][prevIndex]);
        }
      }
    }

    int ans = Integer.MAX_VALUE;
    for(int index: indexes[key.charAt(key.length() - 1) - 'a']) {
      ans = Math.min(ans, dp[key.length()][index]);
    }
    return ans;
  }
}
