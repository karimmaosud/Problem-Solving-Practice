package com.mirak.leetcode.individual.medium;

import java.util.*;

public class MeetingRoomsII {

  private class Interval {

    int start;
    int end;

    Interval() {
      start = 0;
      end = 0;
    }

    Interval(int s, int e) {
      start = s;
      end = e;
    }
  }

  private class Point {

    int x;
    boolean end;

    Point(int x, boolean end) {
      this.x = x;
      this.end = end;
    }
  }

  public int minMeetingRooms(Interval[] intervals) {
    if (intervals.length == 0) {
      return 0;
    }
    ArrayList<Point> points = new ArrayList<>();
    for (Interval interval : intervals) {
      points.add(new Point(interval.start, false));
      points.add(new Point(interval.end, true));
    }
    Collections.sort(points, new Comparator<Point>() {
      @Override
      public int compare(Point p1, Point p2) {
        int diff = p1.x - p2.x;
        return diff != 0 ? diff : p1.end ? -1 : 1;
      }
    });
    int current = 0;
    int max = 0;
    for (Point point : points) {
      if (point.end) {
        current--;
      } else {
        current++;
      }
      max = Math.max(max, current);
    }
    return max;
  }
}
