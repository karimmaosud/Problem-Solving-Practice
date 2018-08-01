package com.mirak.leetcode.individual.hard;

import java.util.*;

public class PerfectRectangle {

  private class Segment {

    int left, right;

    Segment(int left, int right) {
      this.left = left;
      this.right = right;
    }
  }

  public boolean isRectangleCover(int[][] rectangles) {
    Arrays.sort(rectangles, new Comparator<int[]>() {
      @Override
      public int compare(int[] rec1, int[] rec2) {
        //[x1, y1, x2, y2]
        int yDiff = rec1[1] - rec2[1];
        if (yDiff != 0) {
          return yDiff;
        }
        return rec1[0] - rec2[0];
      }
    });

    Set<Integer> set = new HashSet<>();

    PriorityQueue<Integer> pq = new PriorityQueue<>();
    Map<Integer, LinkedList<Segment>> map = new HashMap<>();

    if (!processFirstSegment(rectangles, map)) {
      // Fragments in the min y.
      return false;
    }

    pq.add(rectangles[0][1]);
    set.add(rectangles[0][1]);

    for (int i = 0; i < rectangles.length; i++) {
      int[] rectangle = rectangles[i];

      if (rectangle[1] > pq.peek()) {
        // We've finished processing this y.
        if (!map.get(pq.peek()).isEmpty()) {
          // Its segment should be consumed entirely.
          return false;
        }
        pq.poll();
        if (!pq.isEmpty() && !adjustAndMerge(pq.peek(), map)) {
          return false;
        }
      }

      if (pq.isEmpty() || rectangle[1] != pq.peek()) {
        // We can't have an empty queue at any point of time.
        // current (min) y != pq.peek() is an invalid move along the segments. It could be greater or smaller to pq.peek(), both cases are invalid.
        return false;
      }

      if (!consumeRectangleBound(rectangle[1], rectangle[0], rectangle[2], map)) {
        return false;
      }

      addRectangleBoundToMap(rectangle[3], rectangle[0], rectangle[2], map);

      if (!set.contains(rectangle[3])) {
        set.add(rectangle[3]);
        pq.add(rectangle[3]);
      }

    }
    while (!pq.isEmpty() && map.get(pq.peek()).size() == 0) {
      pq.poll();
    }

    if (pq.size() > 1) {
      return false;
    }

    return adjustAndMerge(pq.peek(), map);

  }

  private boolean processFirstSegment(int[][] rectangles,
      Map<Integer, LinkedList<Segment>> map) {
    int y = rectangles[0][1];
    for (int i = 0; i < rectangles.length && rectangles[i][1] == y; i++) {
      addRectangleBoundToMap(y, rectangles[i][0], rectangles[i][2], map);
    }
    adjustAndMerge(y, map);
    return map.get(y).size() == 1;
  }

  private boolean consumeRectangleBound(int y, int x1, int x2,
      Map<Integer, LinkedList<Segment>> map) {
    if (!map.containsKey(y)) {
      return false;
    }
    Segment segment = map.get(y).getFirst();
    if (x1 != segment.left || x2 > segment.right) {
      return false;
    }
    if (x2 == segment.right) {
      // segment has been consumed entirely.
      map.get(y).removeFirst();
    } else {
      // update segment's left to the end of the consumed bound (x2).
      segment.left = x2;
    }
    return true;
  }

  private void addRectangleBoundToMap(int y, int x1, int x2,
      Map<Integer, LinkedList<Segment>> map) {
    map.putIfAbsent(y, new LinkedList<>());
    map.get(y).addLast(new Segment(x1, x2));
  }

  private boolean adjustAndMerge(int y, Map<Integer, LinkedList<Segment>> map) {
    LinkedList<Segment> segments = map.get(y);
    Collections.sort(segments, new Comparator<Segment>() {
      @Override
      public int compare(Segment o1, Segment o2) {
        return o1.left - o2.left;
      }
    });

    LinkedList<Segment> merged = new LinkedList<>();
    merged.addLast(segments.removeFirst());
    while (!segments.isEmpty()) {
      Segment current = segments.removeFirst();
      if (current.left < merged.getLast().right) {
        return false;
      }
      if (current.left == merged.getLast().right) {
        merged.getLast().right = current.right;
      } else {
        merged.addLast(current);
      }
    }
    map.put(y, merged);
    return true;
  }
}
