package com.mirak.leetcode.individual.medium;

import java.util.*;

public class CourseScheduleII {

  private enum State {
    UNVISITED, VISITING, VISITED
  }

  public int[] findOrder(int numCourses, int[][] prerequisites) {
    int n = numCourses;
    int[][] edges = prerequisites;
    ArrayList<Integer>[] adjList = new ArrayList[numCourses];
    for (int i = 0; i < n; i++) {
      adjList[i] = new ArrayList<>();
    }
    for (int[] edge : edges) {
      adjList[edge[0]].add(edge[1]);
    }
    State[] state = new State[n];
    Arrays.fill(state, State.UNVISITED);
    LinkedList<Integer> order = new LinkedList<>();
    for (int i = 0; i < n; i++) {
      if (state[i] == State.UNVISITED && hasCycle(i, adjList, state, order)) {
        return new int[]{};
      }
    }
    int[] orderArray = new int[n];
    for (int i = 0; i < orderArray.length; i++) {
      orderArray[i] = order.removeFirst();
    }
    return orderArray;
  }

  private boolean hasCycle(int node, ArrayList<Integer>[] adjList, State[] state,
      LinkedList<Integer> order) {

    state[node] = State.VISITING;

    for (int next : adjList[node]) {
      if (state[next] == State.VISITING) {
        return true;
      }
      if (state[next] == State.UNVISITED && hasCycle(next, adjList, state, order)) {
        return true;
      }
    }
    order.addLast(node);
    state[node] = State.VISITED;
    return false;
  }
}
