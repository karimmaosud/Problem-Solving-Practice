package com.mirak.leetcode.individual.medium;

import java.awt.Point;
import java.util.*;

public class MinimumAreaRectangleII {

  private final double INF = 1000000000000.0;

  public double minAreaFreeRect(int[][] points) {
    Set<Point> set = new HashSet<>();
    for (int[] point : points) {
      set.add(new Point(point[0], point[1]));
    }
    int n = points.length;
    double ans = INF;
    for (int i = 0; i < n; ++i) {
      for (int j = 0; j < n; ++j) {
        if (i == j) {
          continue;
        }
        for (int k = 0; k < n; ++k) {
          if (k == i || k == j || !isPerpendicular(points[i], points[j], points[k])) {
            continue;
          }
          Point p4 = new Point(points[i][0] + points[k][0] - points[j][0],
              points[i][1] + points[k][1] - points[j][1]);
          if (set.contains(p4)) {
            ans = Math.min(ans, getArea(points[i], points[j], points[k]));
          }
        }
      }
    }
    return Math.abs(ans - INF) <= 1e-8 ? 0 : ans;
  }

  private boolean isPerpendicular(int[] pi, int[] pj, int[] pk) {
    return ((pi[0] - pj[0]) * (pk[0] - pj[0])) + (pi[1] - pj[1]) * (pk[1] - pj[1]) == 0;
  }

  private double getArea(int[] pi, int[] pj, int[] pk) {
    return getLength(pi, pj) * getLength(pj, pk);
  }

  private double getLength(int[] pi, int[] pj) {
    return Math.sqrt((pi[0] - pj[0]) * (pi[0] - pj[0]) + (pi[1] - pj[1]) * (pi[1] - pj[1]));
  }

}
