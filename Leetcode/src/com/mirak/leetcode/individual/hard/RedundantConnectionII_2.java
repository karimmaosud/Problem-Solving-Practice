package com.mirak.leetcode.individual.hard;

import java.util.*;

public class RedundantConnectionII_2 {

  private enum State {
    UNVISITED, VISITING, VISITED
  }

  public int[] findRedundantDirectedConnection(int[][] edges) {
    int n = edges.length;
    int[] p = new int[n + 1];
    Arrays.fill(p, -1);

    ArrayList<Integer>[] adjList = new ArrayList[n + 1];
    State[] state = new State[n + 1];

    initGraph(edges, state, adjList);

    int[] cycleEdge = null;
    for (int i = 1; i <= n; i++) {
      if (cycleEdge != null) {
        break;
      }
      if (state[i] == State.UNVISITED) {
        cycleEdge = hasCycle(i, -1, adjList, state, p);
      }
    }

    if (cycleEdge == null) {
      return getNoCycleEdge(p, edges);
    }

    return getCycleEdge(p, edges, cycleEdge);
  }

  private void initGraph(int[][] edges, State[] state, ArrayList<Integer>[] adjList) {
    for (int i = 1; i <= edges.length; i++) {
      adjList[i] = new ArrayList<>();
      state[i] = State.UNVISITED;
    }
    for (int[] edge : edges) {
      int u = edge[0];
      int v = edge[1];
      adjList[u].add(v);
    }
  }

  private int[] hasCycle(int node, int parent, ArrayList<Integer>[] adjList, State[] state,
      int[] p) {

    if (state[node] == State.VISITING) {
      // cycle.
      return new int[]{parent, node};
    }

    state[node] = State.VISITING;
    int[] edge = null;
    for (int v : adjList[node]) {
      p[v] = node;
      if (state[v] != State.VISITED) {
        int[] cycleEdge = hasCycle(v, node, adjList, state, p);
        if (cycleEdge != null) {
          edge = cycleEdge;
        }
      }
    }
    state[node] = State.VISITED;
    return edge;
  }

  private int[] getNoCycleEdge(int[] p, int[][] edges) {
    int node = getMultiParentNode(p, edges);
    for (int i = edges.length - 1; i >= 0; i--) {
      if (edges[i][1] == node) {
        return new int[]{edges[i][0], node};
      }
    }
    return null;
  }


  private int[] getCycleEdge(int[] p, int[][] edges, int[] cycleEdge) {
    Map<String, Integer> edgeIndex = new HashMap<>();
    for (int i = 0; i < edges.length; i++) {
      edgeIndex.put(edges[i][0] + "#" + edges[i][1], i);
    }
    int[] edge = new int[2];
    int maxIndex = -1;
    int left = cycleEdge[0];
    int right = cycleEdge[1];
    String edgeHash;
    while (left != cycleEdge[1]) {
      edgeHash = left + "#" + right;
      if (edgeIndex.get(edgeHash) > maxIndex) {
        edge[0] = left;
        edge[1] = right;
        maxIndex = edgeIndex.get(edgeHash);
      }
      edgeIndex.put(edgeHash, -1);
      right = left;
      left = p[left];
    }
    edgeHash = left + "#" + right;
    if (edgeIndex.get(edgeHash) > maxIndex) {
      edge[0] = left;
      edge[1] = right;
    }
    int multiParentNode = getMultiParentNode(p, edges);

    if (multiParentNode < 0) {
      return edge;
    }
    for (int i = 0; i < edges.length; i++) {
      edgeHash = edges[i][0] + "#" + edges[i][1];
      if (edgeIndex.get(edgeHash) < 0 && edges[i][1] == multiParentNode) {
        return edges[i];
      }
    }
    return edge;
  }

  private static int getMultiParentNode(int[] p, int[][] edges) {
    Arrays.fill(p, -1);
    for (int[] edge : edges) {
      if (p[edge[1]] != -1) {
        return edge[1];
      }
      p[edge[1]] = edge[0];
    }
    return -2;
  }
}
