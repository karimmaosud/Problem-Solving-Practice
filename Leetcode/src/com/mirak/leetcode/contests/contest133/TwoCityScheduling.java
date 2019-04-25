package com.mirak.leetcode.contests.contest133;

import java.util.*;

public class TwoCityScheduling {

  public int twoCitySchedCost(int[][] costs) {
    Arrays.sort(costs, (int[] a, int[] b) -> {
      int d1 = a[0] - a[1];
      int d2 = b[0] - b[1];
      return d1 - d2;
    });
    int res = 0;
    for (int i = 0; i < costs.length; ++i) {
      if (i < costs.length / 2) {
        res += costs[i][0];
      } else {
        res += costs[i][1];
      }
    }
    return res;
  }

}
