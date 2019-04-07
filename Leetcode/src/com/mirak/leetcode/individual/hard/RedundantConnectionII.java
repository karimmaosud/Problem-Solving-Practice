package com.mirak.leetcode.individual.hard;

import java.util.*;

public class RedundantConnectionII {

  private final int SHIFT = 10;

  public int[] findRedundantDirectedConnection(int[][] edges) {
    int n = edges.length;
    ArrayList<Integer>[] adjList = new ArrayList[n + 1];
    ArrayList<Integer>[] parents = new ArrayList[n + 1];
    for (int i = 0; i <= n; ++i) {
      adjList[i] = new ArrayList<>();
      parents[i] = new ArrayList<>();
    }
    init(edges, adjList, parents);
    Map<Integer, Integer> edgeRank = getEdgeRank(edges);

    int multiParentNode = getMultiParentNode(parents);
    if (multiParentNode != -1) {
      int[] edge1 = new int[]{parents[multiParentNode].get(0), multiParentNode};
      int[] edge2 = new int[]{parents[multiParentNode].get(1), multiParentNode};
      int n1 = dfs(-1, getRoot(parents), adjList, edge1);
      int n2 = dfs(-1, getRoot(parents), adjList, edge2);

      if (n1 < n) {
        return edge2;
      }
      if (n2 < n) {
        return edge1;
      }
      return edgeRank.get(encodeEdge(edge1)) > edgeRank.get(encodeEdge(edge2)) ? edge1 : edge2;
    }
    int[] cycleEdge = getCycleEdge(parents);
    int rank = edgeRank.get(encodeEdge(cycleEdge));
    int[] removedEdge = cycleEdge, runnerEdge = cycleEdge;

    while (!Arrays.equals((runnerEdge = getNext(runnerEdge, parents)), cycleEdge)) {
      if (edgeRank.get(encodeEdge(runnerEdge)) > rank) {
        rank = edgeRank.get(encodeEdge(runnerEdge));
        removedEdge = runnerEdge;
      }
    }
    return removedEdge;
  }

  private void init(int[][] edges, ArrayList<Integer>[] adjList,
      ArrayList<Integer>[] parents) {
    for (int[] edge : edges) {
      adjList[edge[0]].add(edge[1]);
      parents[edge[1]].add(edge[0]);
    }
  }

  private int getMultiParentNode(ArrayList<Integer>[] parents) {
    for (int i = 1; i < parents.length; ++i) {
      if (parents[i].size() > 1) {
        return i;
      }
    }
    return -1;
  }

  private int getRoot(ArrayList<Integer>[] parents) {
    for (int i = 1; i < parents.length; ++i) {
      if (parents[i].isEmpty()) {
        return i;
      }
    }
    return -1;
  }

  private int dfs(int prev, int node, ArrayList<Integer>[] adjList, int[] cutEdge) {
    int count = 1;
    for (int v : adjList[node]) {
      if (prev == v || (node == cutEdge[0] && v == cutEdge[1])) {
        continue;
      }
      count += dfs(node, v, adjList, cutEdge);
    }
    return count;
  }

  private Map<Integer, Integer> getEdgeRank(int[][] edges) {
    Map<Integer, Integer> edgeRank = new HashMap<>();
    for (int i = 0; i < edges.length; ++i) {
      edgeRank.put(encodeEdge(edges[i]), i);
    }
    return edgeRank;
  }

  private int encodeEdge(int[] edge) {
    return (edge[0] << SHIFT) | edge[1];
  }

  private int[] getCycleEdge(ArrayList<Integer>[] parents) {
    int node = 1;
    boolean[] vis = new boolean[parents.length];
    vis[1] = true;
    while (!vis[parents[node].get(0)]) {
      node = parents[node].get(0);
      vis[node] = true;
    }
    return new int[]{parents[node].get(0), node};
  }

  private int[] getNext(int[] edge, ArrayList<Integer>[] parents) {
    return new int[]{parents[edge[0]].get(0), edge[0]};
  }
}
