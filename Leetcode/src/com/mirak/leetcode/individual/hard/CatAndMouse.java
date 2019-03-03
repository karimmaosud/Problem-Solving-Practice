package com.mirak.leetcode.individual.hard;

import java.util.*;

public class CatAndMouse {

  private final int MOUSE = 1;
  private final int CAT = 2;
  private final int DRAW = 0;

  public int catMouseGame(int[][] graph) {
    int n = graph.length;
    int[][][] dp = new int[n][n][3];

    int[][][] childCount = new int[n][n][3];

    for (int i = 1; i < n; i++) {
      for (int j = 0; j < n; j++) {
        childCount[i][j][CAT] = graph[i].length;
        childCount[i][j][MOUSE] = graph[j].length;
        for (int next : graph[i]) {
          if (next == 0) {
            childCount[i][j][CAT]--;
            break;
          }
        }
      }
    }

    Queue<int[]> queue = new LinkedList<>();
    for (int i = 1; i < n; i++) {
      for (int t = 1; t <= 2; t++) {
        dp[i][0][t] = MOUSE;
        queue.add(new int[]{i, 0, t, MOUSE});
        dp[i][i][t] = CAT;
        queue.add(new int[]{i, i, t, CAT});
      }
    }

    while (!queue.isEmpty()) {
      int[] childState = queue.poll();

      int c = childState[0];
      int m = childState[1];
      int turn = childState[2];
      int winner = childState[3];

      for (int parent : turn == CAT ? graph[m] : graph[c]) {

        if (parent == 0 && turn == MOUSE) {
          continue;
        }

        int parentMouse = turn == CAT ? parent : m;
        int parentCat = turn == CAT ? c : parent;
        int parentTurn = turn ^ 3;

        if (dp[parentCat][parentMouse][parentTurn] != DRAW) {
          continue;
        }

        if (parentTurn == winner) {
          dp[parentCat][parentMouse][parentTurn] = winner;
          queue.add(new int[]{parentCat, parentMouse, parentTurn, winner});
        } else {

          childCount[parentCat][parentMouse][parentTurn]--;

          if (childCount[parentCat][parentMouse][parentTurn] == 0) {
            dp[parentCat][parentMouse][parentTurn] = parentTurn ^ 3;
            queue.add(new int[]{parentCat, parentMouse, parentTurn, parentTurn ^ 3});
          }
        }
      }
    }

    return dp[2][1][MOUSE];
  }
}
