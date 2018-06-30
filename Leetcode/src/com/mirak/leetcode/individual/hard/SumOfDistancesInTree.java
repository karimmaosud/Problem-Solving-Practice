package com.mirak.leetcode.individual.hard;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class SumOfDistancesInTree {

  public int[] sumOfDistancesInTree(int N, int[][] edges) {
    int[] sum = new int[N];
    if (N <= 1) {
      return sum;
    }
    int[] treeSize = new int[N];

    ArrayList<Integer>[] adjList = new ArrayList[N];
    for (int i = 0; i < N; i++) {
      adjList[i] = new ArrayList<>();
    }

    for (int[] edge : edges) {
      int u = edge[0];
      int v = edge[1];
      adjList[u].add(v);
      adjList[v].add(u);
    }
    boolean[] vis = new boolean[N];
    vis[0] = true;
    dfs(0, sum, treeSize, adjList, vis, 0);
    bfs(treeSize, sum, adjList);
    return sum;
  }

  private int dfs(int node, int[] sum, int[] treeSize, ArrayList<Integer>[] adjList, boolean[] vis,
      int dist) {
    sum[0] += dist;
    int nodes = 1;
    for (int i = 0; i < adjList[node].size(); i++) {
      int v = adjList[node].get(i);
      if (!vis[v]) {
        vis[v] = true;
        nodes += dfs(v, sum, treeSize, adjList, vis, dist + 1);
      }
    }
    return treeSize[node] = nodes;
  }

  private void bfs(int[] treeSize, int[] sum, ArrayList<Integer>[] adjList) {
    int N = adjList.length;
    Queue<Integer> queue = new LinkedList<>();
    boolean[] vis = new boolean[N];
    vis[0] = true;
    queue.add(0);
    while (!queue.isEmpty()) {
      int node = queue.poll();
      for (int i = 0; i < adjList[node].size(); i++) {
        int v = adjList[node].get(i);
        if (vis[v]) {
          continue;
        }
        vis[v] = true;
        sum[v] = sum[node] + N - 2 * treeSize[v];
        queue.add(v);
      }
    }
  }
}
