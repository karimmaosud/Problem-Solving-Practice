package com.mirak.leetcode.individual.medium;

import java.util.*;

public class CourseSchedule {

  private enum State {
    UNVISITED, VISITING, VISITED
  }

  public boolean canFinish(int numCourses, int[][] prerequisites) {
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
    for (int i = 0; i < n; i++) {
      if (state[i] == State.UNVISITED && hasCycle(i, adjList, state)) {
        return false;
      }
    }
    return true;
  }

  private boolean hasCycle(int node, ArrayList<Integer>[] adjList, State[] state) {

    state[node] = State.VISITING;

    for (int next : adjList[node]) {
      if (state[next] == State.VISITING) {
        return true;
      }
      if (state[next] == State.UNVISITED && hasCycle(next, adjList, state)) {
        return true;
      }
    }
    state[node] = State.VISITED;
    return false;
  }

}
