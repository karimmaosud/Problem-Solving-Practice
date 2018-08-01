package com.mirak.leetcode.individual.hard;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class PerfectRectangle_Optimized {

  public boolean isRectangleCover(int[][] rectangles) {
    int minX = Integer.MAX_VALUE, maxX = Integer.MIN_VALUE;
    int minY = Integer.MAX_VALUE, maxY = Integer.MIN_VALUE;

    Map<String, Integer> map = new HashMap<>();
    int area = 0;

    for (int i = 0; i < rectangles.length; i++) {
      int[] rectangle = rectangles[i];
      int xLeft = rectangle[0];
      int xRight = rectangle[2];
      int yDown = rectangle[1];
      int yUp = rectangle[3];

      area += (xRight - xLeft) * (yUp - yDown);

      minX = Math.min(minX, xLeft);
      maxX = Math.max(maxX, xRight);
      minY = Math.min(minY, yDown);
      maxY = Math.max(maxY, yUp);

      incrementPointOccurrence(makeStringFromPoint(xLeft, yDown), map);
      incrementPointOccurrence(makeStringFromPoint(xRight, yDown), map);
      incrementPointOccurrence(makeStringFromPoint(xLeft, yUp), map);
      incrementPointOccurrence(makeStringFromPoint(xRight, yUp), map);
    }

    Set<String> set = new HashSet<>();
    set.add(makeStringFromPoint(minX, minY));
    set.add(makeStringFromPoint(maxX, minY));
    set.add(makeStringFromPoint(minX, maxY));
    set.add(makeStringFromPoint(maxX, maxY));

    int oneCount = 0;

    for (Entry<String, Integer> entry : map.entrySet()) {
      if (entry.getValue() != 1 && entry.getValue() != 2 && entry.getValue() != 4) {
        return false;
      }
      if (entry.getValue() == 1) {
        oneCount++;
        if (!set.contains(entry.getKey())) {
          return false;
        }
      }
    }

    return oneCount == 4 && area == (maxX - minX) * (maxY - minY);
  }

  private String makeStringFromPoint(int x, int y) {
    return "" + x + "$" + y;
  }

  private void incrementPointOccurrence(String point, Map<String, Integer> map) {
    map.put(point, map.getOrDefault(point, 0) + 1);
  }
}
