package com.mirak.leetcode.individual.medium;

import java.util.*;

public class MinimumHeightTrees2 {

  public List<Integer> findMinHeightTrees(int n, int[][] edges) {
    List<Integer> roots = new LinkedList<>();
    if (edges.length == 0) {
      for (int i = 0; i < n; i++) {
        roots.add(i);
      }
      return roots;
    }

    ArrayList<Integer>[] adjList = new ArrayList[n];
    for (int i = 0; i < adjList.length; i++) {
      adjList[i] = new ArrayList<>();
    }

    for (int[] edge : edges) {
      adjList[edge[0]].add(edge[1]);
      adjList[edge[1]].add(edge[0]);
    }

    int[] height = new int[n];
    setHeights(0, -1, adjList, height);

    int[] rootedHeights = new int[n];

    setRootedHeight(0, -1, 0, adjList, height, rootedHeights);

    int min = Integer.MAX_VALUE;
    for (int rootedHeight : rootedHeights) {
      min = Math.min(min, rootedHeight);
    }
    for (int i = 0; i < n; i++) {
      if (rootedHeights[i] == min) {
        roots.add(i);
      }
    }
    return roots;
  }

  private void setRootedHeight(int i, int p, int maxHeight, ArrayList<Integer>[] adjList,
      int[] height, int[] rootedHeights) {

    rootedHeights[i] = Math.max(height[i], p == -1 ? 0 : maxHeight);

    int[] max = new int[2];
    for (int v : adjList[i]) {
      if (v == p) {
        continue;
      }
      if (height[v] + 1 > max[1]) {
        max[1] = height[v] + 1;
        if (max[1] > max[0]) {
          swap(max, 0, 1);
        }
      }
    }

    for (int v : adjList[i]) {
      if (v == p) {
        continue;
      }
      int maxSibling = height[v] + 1 == max[0] ? max[1] : max[0];
      setRootedHeight(v, i, Math.max(maxHeight + 1, maxSibling + 1), adjList, height,
          rootedHeights);
    }
  }

  private int setHeights(int i, int p, ArrayList<Integer>[] adjList, int[] height) {
    int h = 0;
    for (int v : adjList[i]) {
      if (v == p) {
        continue;
      }
      h = Math.max(h, setHeights(v, i, adjList, height) + 1);
    }
    return height[i] = h;
  }

  private void swap(int[] ar, int i, int j) {
    int temp = ar[i];
    ar[i] = ar[j];
    ar[j] = temp;
  }
}
