package com.mirak.leetcode.individual.medium;

import java.util.*;

public class ConvexPolygon {

  public boolean isConvex(List<List<Integer>> points) {
    if (convexCheck(points)) {
      return true;
    }
    Collections.reverse(points);
    return convexCheck(points);
  }

  private int direction(List<Integer> p0, List<Integer> p1, List<Integer> p2) {
    int x1 = p2.get(0) - p0.get(0);
    int x2 = p1.get(0) - p0.get(0);
    int y1 = p2.get(1) - p0.get(1);
    int y2 = p1.get(1) - p0.get(1);
    return x1 * y2 - x2 * y1;
  }

  private boolean convexCheck(List<List<Integer>> points) {

    for (int i = 0; i + 3 <= points.size(); ++i) {
      if (direction(points.get(i), points.get(i + 1), points.get(i + 2)) > 0) {
        return false;
      }
    }

    return direction(points.get(points.size() - 1), points.get(0),
        points.get(1)) <= 0
        && direction(points.get(points.size() - 2), points.get(points.size() - 1),
        points.get(0)) <= 0;
  }
}
