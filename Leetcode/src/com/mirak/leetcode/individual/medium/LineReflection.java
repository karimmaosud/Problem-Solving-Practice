package com.mirak.leetcode.individual.medium;

import java.awt.Point;
import java.util.*;

public class LineReflection {

  public boolean isReflected(int[][] points) {

    Set<Point> set = new HashSet<>();
    for (int[] point : points) {
      set.add(new Point(point[0], point[1]));
    }

    if (set.isEmpty()) {
      return true;
    }
    int midPoint = getMidPoint(set);
    for (Point p : set) {
      if (!set.contains(new Point(midPoint - p.x, p.y))) {
        return false;
      }
    }
    return true;
  }

  private int getMidPoint(Set<Point> set) {
    int y = set.iterator().next().y;
    int minX = Integer.MAX_VALUE;
    int maxX = Integer.MIN_VALUE;
    for (Point p : set) {
      if (p.y == y) {
        minX = Math.min(minX, p.x);
        maxX = Math.max(maxX, p.x);
      }
    }
    return minX + maxX;
  }
}
