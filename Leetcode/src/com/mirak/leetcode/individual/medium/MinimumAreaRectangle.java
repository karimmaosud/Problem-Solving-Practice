package com.mirak.leetcode.individual.medium;

import java.util.*;

public class MinimumAreaRectangle {

  private int scale = 45000;

  public int minAreaRect(int[][] points) {
    Set<Integer> set = new HashSet<>();
    for (int[] point : points) {
      set.add(point[0] * scale + point[1]);
    }
    int minArea = Integer.MAX_VALUE;
    for (int i = 0; i < points.length; i++) {
      int hashI = points[i][0] * scale + points[i][1];
      for (int j = i + 1; j < points.length; j++) {
        int hashJ = points[j][0] * scale + points[j][1];
        int hashK = points[j][0] * scale + points[i][1];
        int hashL = points[i][0] * scale + points[j][1];
        if (hashK == hashI || hashK == hashJ || hashL == hashI || hashL == hashJ || !set
            .contains(hashK) || !set.contains(hashL)) {
          continue;
        }
        minArea = Math.min(minArea,
            Math.abs(points[i][0] - points[j][0]) * Math.abs(points[i][1] - points[j][1]));

      }
    }
    return minArea % Integer.MAX_VALUE;
  }
}
