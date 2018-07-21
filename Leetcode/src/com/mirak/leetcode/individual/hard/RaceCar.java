package com.mirak.leetcode.individual.hard;

import java.util.PriorityQueue;

public class RaceCar {

  private class Node {

    int location;
    int steps;
    int direction;

    Node(int location, int steps, int direction) {
      this.location = location;
      this.steps = steps;
      this.direction = direction;
    }

  }

  public int racecar(int target) {
    int boundPower = Math.max(((int) Math.ceil(Math.log10(1.0 * target) / Math.log10(2.0))), 1);
    int bound = 1 << boundPower;
    int[][] dist = new int[2 * bound + 1][2];

    for (int i = 0; i < dist.length; i++) {
      dist[i][0] = dist[i][1] = Integer.MAX_VALUE;
    }

    PriorityQueue<Node> pq = new PriorityQueue<>((a, b) -> a.steps - b.steps);
    pq.add(new Node(0, 0, 0));
    dist[0][0] = 0;

    while (!pq.isEmpty()) {
      Node top = pq.poll();
      int location = top.location;
      int steps = top.steps;
      int direction = top.direction;

      if (dist[Math.floorMod(location, dist.length)][direction] != steps) {
        continue;
      }

      for (int i = 1; i <= boundPower; i++) {
        int nextLeft = location + ((1 << i) - 1);
        int leftCost = steps + i;
        if (location != 0) {
          leftCost += (direction == 0 ? 2 : 1);
        }

        if (checkBound(nextLeft, bound) && leftCost < dist[Math
            .floorMod(nextLeft, dist.length)][0]) {
          dist[Math.floorMod(nextLeft, dist.length)][0] = leftCost;
          pq.offer(new Node(nextLeft, leftCost, 0));
        }
        int nextRight = location - ((1 << i) - 1);
        int rightCost = steps + i + (direction == 1 ? 2 : 1);
        if (checkBound(nextRight, bound) && rightCost < dist[Math
            .floorMod(nextRight, dist.length)][1]) {
          dist[Math.floorMod(nextRight, dist.length)][1] = rightCost;
          pq.offer(new Node(nextRight, rightCost, 1));
        }
      }
    }

    return Math.min(dist[target][0], dist[target][1]);
  }

  private boolean checkBound(int location, int bound) {
    return Math.abs(location) <= bound;
  }
}
