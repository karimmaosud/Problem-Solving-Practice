package com.mirak.leetcode.individual.medium;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class MinimumHeightTrees {

  private int inf = 1000000000;

  public List<Integer> findMinHeightTrees(int n, int[][] edges) {

    List<Integer> res = new LinkedList<>();

    if (n == 0) {
      return res;
    }
    if (n == 1) {
      res.add(0);
      return res;
    }

    ArrayList<Integer>[] adjList = new ArrayList[n];
    for (int i = 0; i < n; i++) {
      adjList[i] = new ArrayList<>();
    }
    for (int[] edge : edges) {
      adjList[edge[0]].add(edge[1]);
      adjList[edge[1]].add(edge[0]);
    }

    int[] dist = new int[n];
    int[] parent = new int[n];
    boolean[] vis = new boolean[n];
    for (int i = 0; i < n; i++) {
      dist[i] = inf;
      parent[i] = -1;
    }

    dist[0] = 0;
    vis[0] = true;
    int maxHeight = dfs(0, adjList, dist, parent, vis);

    int sourceNode = -1;
    for (int i = 1; i < n; i++) {
      if (dist[i] == maxHeight) {
        sourceNode = i;
        break;
      }
    }

    vis = new boolean[n];
    vis[sourceNode] = true;
    int diameter = dfs(sourceNode, adjList, null, null, vis);

    int height = 0;
    int upHeight = dist[sourceNode];
    int secondMaxHeight = diameter - upHeight;

    while (sourceNode != -1) {
      if (height == diameter - diameter / 2
          || upHeight + secondMaxHeight == diameter - diameter / 2) {
        res.add(sourceNode);
      }
      sourceNode = parent[sourceNode];
      height++;
      upHeight--;
    }
    return res;
  }

  private int dfs(int node, ArrayList<Integer>[] adjList, int[] dist, int[] parent,
      boolean[] vis) {

    int height = 0;

    for (int i = 0; i < adjList[node].size(); i++) {
      int v = adjList[node].get(i);
      if (vis[v]) {
        continue;
      }

      vis[v] = true;

      if (dist != null) {
        dist[v] = dist[node] + 1;
      }

      if (parent != null) {
        parent[v] = node;
      }

      height = Math.max(height, dfs(v, adjList, dist, parent, vis) + 1);
    }
    return height;
  }

}
