package com.mirak.leetcode.contests.contest125;

public class FindTheTownJudge {
  public int findJudge(int N, int[][] trust) {
    int[] trustCount = new int[N + 1];
    boolean[] vis = new boolean[N + 1];
    for (int[] tuple : trust) {
      trustCount[tuple[1]]++;
      vis[tuple[0]] = true;
    }
    int judge = -1;
    for (int i = 1; i <= N; i++) {
      if (!vis[i] && trustCount[i] == N - 1) {
        if (judge != -1) {
          return -1;
        }
        judge = i;
      }
    }
    return judge;
  }

}
