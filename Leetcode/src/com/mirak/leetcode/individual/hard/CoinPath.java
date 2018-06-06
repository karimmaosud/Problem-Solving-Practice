package com.mirak.leetcode.individual.hard;

import java.util.LinkedList;
import java.util.List;

public class CoinPath {
  private int inf = 1000000000;
  public List<Integer> cheapestJump(int[] A, int B) {
    List<Integer> list = new LinkedList<>();
    if(A.length == 0 || A[A.length - 1] == -1) {
      return list;
    }

    int[] dp = new int[A.length];
    int[] path = new int[A.length];
    for(int i = 0; i < A.length; i++) {
      dp[i] = -1;
      path[i] = -1;
    }

    int minPathCost = solve(0, A, B, dp, path);
    if(minPathCost == inf) {
      return list;
    }
    int idx = 0;
    while(idx != -1) {
      list.add(idx + 1);
      idx = path[idx];
    }
    return list;
  }

  private int solve(int idx, int[] A, int B, int[] dp, int[] path) {

    if(idx == A.length - 1) {
      return A[A.length - 1];
    }

    if(dp[idx] != -1) {
      return dp[idx];
    }

    int ans = inf;
    for(int i = idx + 1; i <= Math.min(A.length - 1, idx + B); i++) {
      if(A[i] != -1) {
        int pathAns = A[idx] + solve(i, A, B, dp, path);
        if(pathAns < ans) {
          ans = pathAns;
          path[idx] = i;
        }
      }
    }
    return dp[idx] = ans;
  }
}
