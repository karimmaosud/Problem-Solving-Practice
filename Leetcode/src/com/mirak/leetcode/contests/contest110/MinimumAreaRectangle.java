package com.mirak.leetcode.contests.contest110;

import java.util.*;
import java.util.Map.Entry;

public class MinimumAreaRectangle {

  public int minAreaRect(int[][] points) {

    Map<Integer, ArrayList<Integer>> xMap = new TreeMap<>();

    for (int[] point : points) {
      xMap.putIfAbsent(point[0], new ArrayList<>());
      xMap.get(point[0]).add(point[1]);
    }

    Map<Integer,Integer> seenCouples = new HashMap<>();

    int min = Integer.MAX_VALUE;

    for (Entry<Integer,ArrayList<Integer>> entry : xMap.entrySet()) {
      int x = entry.getKey();
      ArrayList<Integer> ys = entry.getValue();
      for (int i = 0; i < ys.size(); i++) {
        for (int j = i + 1; j < ys.size(); j++) {
          int y1 = ys.get(i);
          int y2 = ys.get(j);

          if (y2 < y1) {
            int temp = y2;
            y2 = y1;
            y1 = temp;
          }

          int mapping = 40001 * y1 + y2;
          if (seenCouples.containsKey(mapping)) {
            min = Math.min(min, (y2 - y1) * (x - seenCouples.get(mapping)));
          }
          seenCouples.put(mapping, x);
        }
      }
    }
    return min == Integer.MAX_VALUE ? 0 : min;
  }
}
