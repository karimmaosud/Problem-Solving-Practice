package com.mirak.leetcode.individual.hard;

import java.util.*;

public class ShortestPathVisitingAllNodes {

  private final int INF = 1000000000;

  public int shortestPathLength(int[][] graph) {
    return DpSolution(graph);
  }

  private int DpSolution(int[][] graph) {
    int n = graph.length;
    int[][] dp = new int[n][1 << n];

    for (int[] row : dp) {
      Arrays.fill(row, INF);
    }

    for (int i = 0; i < n; i++) {
      dp[i][1 << i] = 0;
    }

    for (int mask = 0; mask < 1 << n; mask++) {
      for (int node = 0; node < n; node++) {
        if ((mask & (1 << node)) == 0) {
          continue;
        }
        for (int next : graph[node]) {
          dp[next][mask | (1 << next)] = Math.min(dp[next][mask | (1 << next)], 1 + dp[node][mask]);
          dp[node][mask | (1 << next)] = Math
              .min(dp[node][mask | (1 << next)], 1 + dp[next][mask | (1 << next)]);
        }
      }
    }

    int ans = INF;
    for (int i = 0; i < n; i++) {
      ans = Math.min(ans, dp[i][(1 << n) - 1]);
    }
    return ans;
  }


  private class Node {

    int node;
    int mask;

    Node(int node, int mask) {
      this.node = node;
      this.mask = mask;
    }
  }

  private int BfsSolution(int[][] graph) {
    int n = graph.length;
    int[][] d = new int[n][1 << n];
    for (int[] row : d) {
      Arrays.fill(row, INF);
    }
    Queue<Node> q = new LinkedList<>();
    for (int i = 0; i < n; i++) {
      q.add(new Node(i, 1 << i));
      d[i][1 << i] = 0;
    }

    while (!q.isEmpty()) {
      Node top = q.poll();
      int node = top.node;
      int mask = top.mask;

      if (mask == (1 << n) - 1) {
        return d[node][mask];
      }

      for (int next : graph[node]) {
        if (d[node][mask] + 1 < d[next][mask | (1 << next)]) {
          d[next][mask | (1 << next)] = 1 + d[node][mask];
          q.add(new Node(next, mask | (1 << next)));
        }
      }
    }
    return -1;
  }
}
