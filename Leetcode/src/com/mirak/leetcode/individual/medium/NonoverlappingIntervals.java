package com.mirak.leetcode.individual.medium;

import java.util.*;

public class NonoverlappingIntervals {

  public int eraseOverlapIntervals(int[][] intervals) {
    if (intervals.length == 0) {
      return 0;
    }
    List<int[]> list = new ArrayList<>();
    for (int[] interval : intervals) {
      list.add(interval);
    }
    Collections.sort(list, (int[] a, int[] b) -> a[0] - b[0] == 0 ? a[1] - b[1] : a[0] - b[0]);

    int res = 0;
    int end = list.get(0)[1];
    for (int i = 1; i < list.size(); ++i) {
      if (list.get(i)[0] < end) {
        res++;
        end = Math.min(end, list.get(i)[1]);
      } else {
        end = list.get(i)[1];
      }
    }
    return res;
  }
}
