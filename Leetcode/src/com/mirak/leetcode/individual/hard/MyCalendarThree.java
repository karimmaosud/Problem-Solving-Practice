package com.mirak.leetcode.individual.hard;

import java.util.*;

public class MyCalendarThree {

  private class Point {

    int x;
    boolean start;

    Point(int x, boolean start) {
      this.x = x;
      this.start = start;
    }
  }

  ArrayList<Point> points;

  public MyCalendarThree() {
    points = new ArrayList<>();
  }

  public int book(int start, int end) {
    points.add(new Point(start, true));
    points.add(new Point(end - 1, false));

    Collections.sort(points, new Comparator<Point>() {
      @Override
      public int compare(Point o1, Point o2) {
        return o1.x - o2.x != 0 ? o1.x - o2.x : o1.start ? -1 : 1;

      }
    });

    int k = 0;
    int open = 0;
    for (Point p : points) {
      if (p.start) {
        open++;
      } else {
        open--;
      }
      k = Math.max(k, open);
    }
    return k;
  }
}
