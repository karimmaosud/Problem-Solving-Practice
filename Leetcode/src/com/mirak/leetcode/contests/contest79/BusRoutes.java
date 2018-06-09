package com.mirak.leetcode.contests.contest79;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class BusRoutes {
  private class QueueObject {
    int bus;
    int dist;
    QueueObject(int bus, int dist) {
      this.bus = bus;
      this.dist = dist;
    }
  }

  public int numBusesToDestination(int[][] routes, int S, int T) {
    if(S == T) {
      return 0;
    }
    Map<Integer, Set<Integer>> stopsMap = new HashMap<>();
    initStopsMap(routes, stopsMap);
    return bfs(S, T, routes, stopsMap);
  }

  private void initStopsMap(int[][] routes, Map<Integer, Set<Integer>> stopsMap) {
    for(int i = 0; i < routes.length; i++) {
      for(int j = 0; j < routes[i].length; j++) {
        if(!stopsMap.containsKey(routes[i][j])) {
          stopsMap.put(routes[i][j], new HashSet<>());
        }
        stopsMap.get(routes[i][j]).add(i);
      }
    }
  }

  private int bfs(int S, int T, int[][] routes, Map<Integer, Set<Integer>> stopsMap) {
    if(!stopsMap.containsKey(S)) {
      return -1;
    }
    boolean[] busVis = new boolean[routes.length];
    boolean[] stopsVis = new boolean[1000001];
    Queue<QueueObject> queue = new LinkedList<>();
    for(int bus: stopsMap.get(S)) {
      queue.add(new QueueObject(bus, 1));
      busVis[bus] = true;
    }

    while(!queue.isEmpty()) {
      QueueObject queueObject = queue.poll();
      int bus = queueObject.bus;
      int dist = queueObject.dist;
      for(int i = 0; i < routes[bus].length; i++) {
        int busStop = routes[bus][i];
        if(busStop == T) {
          return dist;
        }
        if(stopsVis[busStop]) {
          continue;
        }
        stopsVis[busStop] = true;
        for(int nextBus: stopsMap.get(busStop)) {
          if(!busVis[nextBus]) {
            busVis[nextBus] = true;
            queue.add(new QueueObject(nextBus, dist + 1));
          }
        }
      }
    }
    return -1;
  }
}