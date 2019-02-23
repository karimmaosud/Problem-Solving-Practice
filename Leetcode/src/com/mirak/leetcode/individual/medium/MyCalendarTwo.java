package com.mirak.leetcode.individual.medium;

import java.util.*;

public class MyCalendarTwo {

  private static ArrayList<int[]> events;
  private static ArrayList<int[]> intersections;

  public MyCalendarTwo() {
    events = new ArrayList<>();
    intersections = new ArrayList<>();
  }

  public static boolean book(int start, int end) {

    int[] newEvent = new int[]{start, end};

    for (int[] e : intersections) {
      if (getIntersection(e, newEvent) != null
          || getIntersection(e, newEvent) != null) {
        return false;
      }
    }

    // This event can be added.

    for (int[] e : events) {
      int[] intersection = getIntersection(e, newEvent);
      if (intersection != null) {
        intersections.add(intersection);
      }
    }

    events.add(newEvent);

    return true;
  }

  private static int[] getIntersection(int[] e1, int[] e2) {
    if (e1[0] >= e2[1] || e2[0] >= e1[1]) {
      // no intersection.
      return null;
    }
    return new int[]{Math.max(e1[0], e2[0]), Math.min(e1[1], e2[1])};
  }



}
