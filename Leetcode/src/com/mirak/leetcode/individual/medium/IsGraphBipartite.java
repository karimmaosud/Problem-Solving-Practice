package com.mirak.leetcode.individual.medium;

import java.util.*;

public class IsGraphBipartite {

  public boolean isBipartite(int[][] graph) {

    int[] color = new int[graph.length + 1];
    Arrays.fill(color, -1);

    for (int i = 0; i < graph.length; i++) {
      if (color[i] == -1) {
        if (!bfs(i, color, graph)) {
          return false;
        }
      }
    }
    return true;
  }

  private boolean bfs(int startNode, int[] color, int[][] graph) {

    Queue<Integer> queue = new LinkedList<>();
    color[startNode] = 1;
    queue.add(startNode);
    while (!queue.isEmpty()) {
      int currentNode = queue.poll();
      for (int i = 0; i < graph[currentNode].length; i++) {
        int nextNode = graph[currentNode][i];
        if (color[nextNode] == -1) {
          color[nextNode] = color[currentNode] ^ 1;
          queue.add(nextNode);
        } else if(color[nextNode] != (color[currentNode] ^ 1)) {
          return false;
        }
      }
    }
    return true;
  }
}
