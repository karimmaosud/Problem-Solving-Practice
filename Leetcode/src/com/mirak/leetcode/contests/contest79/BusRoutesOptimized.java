package com.mirak.leetcode.contests.contest79;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class BusRoutesOptimized {
  public int numBusesToDestination(int[][] routes, int S, int T) {

    if(S == T) {
      return 0;
    }

    Set<Integer> startingPoints = new HashSet<>();
    Set<Integer> endingPoints = new HashSet<>();

    ArrayList<Integer>[] adjList = new ArrayList[routes.length];
    for(int i = 0; i < routes.length; i++) {
      for(int j = 0; j < routes[i].length; j++) {
        if(routes[i][j] == S) {
          startingPoints.add(i);
        }else if(routes[i][j] == T) {
          endingPoints.add(i);
        }
      }
      Arrays.sort(routes[i]);
      adjList[i] = new ArrayList<>();
    }
    for(int i = 0; i < routes.length; i++) {
      for(int j = i + 1; j < routes.length; j++) {
        if(hasEdge(routes[i], routes[j])) {
          adjList[i].add(j);
          adjList[j].add(i);
        }
      }
    }

    return bfs(startingPoints, endingPoints, adjList);
  }

  private boolean hasEdge(int[] bus1, int[] bus2) {
    int p1 = 0, p2 = 0;
    while(p1 < bus1.length && p2 < bus2.length) {
      if(bus1[p1] == bus2[p2]) {
        return true;
      }
      if(bus1[p1] < bus2[p2]) {
        p1++;
      }else {
        p2++;
      }
    }
    return false;
  }

  private int bfs(Set<Integer> startingPoints, Set<Integer> endingPoints, ArrayList<Integer>[] adjList) {

    Queue<Point> queue = new LinkedList<>();
    boolean[] vis = new boolean[adjList.length];
    for(int startingBus: startingPoints) {
      vis[startingBus] = true;
      queue.add(new Point(startingBus, 1));
    }



    while(!queue.isEmpty()) {
      Point top = queue.poll();
      int bus = top.x;
      int dist = top.y;
      if(endingPoints.contains(bus)) {
        return dist;
      }
      for(int nextBus: adjList[bus]) {
        if(!vis[nextBus]) {
          vis[nextBus] = true;
          queue.add(new Point(nextBus, dist + 1));
        }
      }
    }
    return -1;
  }
}
