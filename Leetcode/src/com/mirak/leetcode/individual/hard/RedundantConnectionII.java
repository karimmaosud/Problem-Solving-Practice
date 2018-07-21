package com.mirak.leetcode.individual.hard;

import java.util.ArrayList;

public class RedundantConnectionII {

  public int[] findRedundantDirectedConnection(int[][] edges) {

    int n = edges.length;
    int[][] order = new int[n + 1][n + 1];

    ArrayList<Integer>[] parent = new ArrayList[n + 1];
    initList(parent);
    ArrayList<Integer>[] adjList = new ArrayList[n + 1];
    initList(adjList);
    initAdjList(edges, adjList, order, parent);
    boolean[] vis = new boolean[n + 1];
    int startNode = isCycleFromRoot(parent);
    if (startNode == -1) {
      // every node has only one parent, cycle must have gone through root.
      // start with any node (because every node goes back to root through parent), and go until you find an already visited node.
      startNode = 1;
      while (!vis[startNode]) {
        vis[startNode] = true;
        startNode = parent[startNode].get(0);
      }
      return edgeFromCycle(parent, order, startNode);
    }
    int[] removedEdge = new int[2];
    dfs(startNode, adjList, vis);
    int p1 = parent[startNode].get(0);
    int p2 = parent[startNode].get(1);
    if (vis[startNode]) {
      // there must be a cycle, because you have got back to startNode
      setEdge(removedEdge, vis[p1] ? p1 : p2, startNode);
    } else {
      setEdge(removedEdge, order[p1][startNode] > order[p2][startNode] ? p1 : p2, startNode);
    }
    return removedEdge;
  }

  private void initList(ArrayList<Integer>[] list) {
    for (int i = 0; i < list.length; i++) {
      list[i] = new ArrayList<>();
    }
  }

  private void initAdjList(int[][] edges, ArrayList<Integer>[] adjList, int[][] order,
      ArrayList<Integer>[] parent) {
    for (int i = 0; i < edges.length; i++) {
      int[] edge = edges[i];
      order[edge[0]][edge[1]] = i;
      adjList[edge[0]].add(edge[1]);
      parent[edge[1]].add(edge[0]);
    }
  }

  private int isCycleFromRoot(ArrayList<Integer>[] parent) {
    int node = -1;
    for (int i = 0; i < parent.length; i++) {
      if (parent[i].size() == 2) {
        return i;
      }
    }
    return node;
  }

  private void dfs(int node, ArrayList<Integer>[] adjList, boolean[] vis) {
    for (int i = 0; i < adjList[node].size(); i++) {
      int v = adjList[node].get(i);
      if (!vis[v]) {
        vis[v] = true;
        dfs(v, adjList, vis);
      }
    }
  }

  private int[] edgeFromCycle(ArrayList<Integer>[] parent, int[][] order, int startNode) {

    int[] removedEdge = new int[2];

    int start = startNode;
    int p = parent[start].get(0);

    int maxOrder = -1;
    while (p != startNode) {
      // p -> start
      if (order[p][start] > maxOrder) {
        maxOrder = order[p][start];
        setEdge(removedEdge, p, start);
      }
      start = p;
      p = parent[start].get(0);
    }
    // p = startNode, look at last edge.
    if (order[p][start] > maxOrder) {
      setEdge(removedEdge, p, start);
    }
    return removedEdge;
  }

  private void setEdge(int[] edge, int from, int to) {
    edge[0] = from;
    edge[1] = to;
  }

}
