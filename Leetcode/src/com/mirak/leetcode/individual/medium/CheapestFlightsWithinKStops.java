package com.mirak.leetcode.individual.medium;

import java.util.*;

public class CheapestFlightsWithinKStops {

  private static final int INF = 1000000000;

  public static int findCheapestPrice(int n, int[][] flights, int src, int dest, int k) {
    k++;
    ArrayList<int[]>[] adjList = getAdjListFromEdges(flights, n);
    return dijkstraSolve(adjList, n, src, dest, k);
  }

  private static ArrayList<int[]>[] getAdjListFromEdges(int[][] flights, int n) {
    ArrayList<int[]>[] adjList = new ArrayList[n];
    for (int i = 0; i < adjList.length; i++) {
      adjList[i] = new ArrayList<>();
    }
    for (int[] flight : flights) {
      adjList[flight[0]].add(new int[]{flight[1], flight[2]});
    }
    return adjList;
  }

  private int dpSolve(ArrayList<int[]>[] adjList, int n, int src, int dest, int k) {
    int[][] dp = new int[2][n];
    Arrays.fill(dp[0], INF);
    dp[0][src] = 0;
    for (int i = 1; i <= k; i++) {
      int idx = i & 1;
      Arrays.fill(dp[idx], INF);
      for (int j = 0; j < n; j++) {
        for (int[] edge : adjList[j]) {
          int v = edge[0];
          int price = edge[1];
          if (dp[idx][v] > dp[idx ^ 1][j] + price) {
            dp[idx][v] = dp[idx ^ 1][j] + price;
          }
        }
        dp[idx][j] = Math.min(dp[idx][j], dp[idx ^ 1][j]);
      }
    }
    return dp[k & 1][dest] == INF ? -1 : dp[k & 1][dest];
  }

  private static int dijkstraSolve(ArrayList<int[]>[] adjList, int n, int src, int dest, int k) {
    int[][] dist = new int[n][k + 1];
    for (int[] row : dist) {
      Arrays.fill(row, INF);
    }
    dist[src][0] = 0;

    PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
      @Override
      public int compare(int[] o1, int[] o2) {
        return o1[1] - o2[1];
      }
    });

    pq.add(new int[]{src, 0, 0});
    while (!pq.isEmpty()) {
      int[] top = pq.poll();
      int node = top[0];
      int cost = top[1];

      if (node == dest) {
        return cost;
      }

      int level = top[2];

      if (level == k || dist[node][level] != cost) {
        continue;
      }

      for (int[] edge : adjList[node]) {
        int next = edge[0];
        int price = edge[1];
        if (dist[next][level + 1] > dist[node][level] + price) {
          dist[next][level + 1] = dist[node][level] + price;
          pq.add(new int[]{next, dist[next][level + 1], level + 1});
        }
      }
    }

    return -1;
  }
}
