package com.mirak.leetcode.contests.contest119;

import java.awt.Point;
import java.util.*;

public class KClosestPointsToOrigin {

  private class PointDistance implements Comparable<PointDistance> {

    Point p;
    int distance;

    PointDistance(Point p, int distance) {
      this.p = p;
      this.distance = distance;
    }

    @Override
    public int compareTo(PointDistance p) {
      return this.distance - p.distance;
    }

  }

  public int[][] kClosest(int[][] points, int K) {
    PriorityQueue<PointDistance> pq = new PriorityQueue<>(Collections.reverseOrder());
    for (int i = 0; i < points.length; i++) {
      pq.add(new PointDistance(new Point(points[i][0], points[i][1]),
          points[i][0] * points[i][0] + points[i][1] * points[i][1]));
      if (pq.size() > K) {
        pq.poll();
      }
    }
    int[][] res = new int[K][2];
    int i = 0;
    while (!pq.isEmpty()) {
      PointDistance top = pq.poll();
      res[i][0] = top.p.x;
      res[i][1] = top.p.y;
      i++;
    }
    return res;
  }
}
