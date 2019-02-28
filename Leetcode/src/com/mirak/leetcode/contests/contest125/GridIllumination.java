package com.mirak.leetcode.contests.contest125;

import java.util.*;

public class GridIllumination {

  private int[] rowInc = {0, 1, -1, 1, 1, -1, -1, 0, 0};
  private int[] colInc = {0, 0, 0, 1, -1, 1, -1, 1, -1};

  private final long SHIFT = 1000000010L;

  public int[] gridIllumination(int N, int[][] lamps, int[][] queries) {
    Map<Integer, Integer> rowLamps = new HashMap<>();
    Map<Integer, Integer> colLamps = new HashMap<>();
    Map<Integer, Integer> dig1Lamps = new HashMap<>();
    Map<Integer, Integer> dig2Lamps = new HashMap<>();
    HashSet<Long> vis = new HashSet<>();
    for (int[] lamp : lamps) {
      int row = lamp[0];
      int col = lamp[1];
      vis.add(row * SHIFT + col);
      incrementCount(rowLamps, row);
      incrementCount(colLamps, col);
      incrementCount(dig1Lamps, col - row);
      incrementCount(dig2Lamps, row + col);
    }
    int[] answer = new int[queries.length];
    for (int i = 0; i < queries.length; i++) {
      int row = queries[i][0];
      int col = queries[i][1];
      boolean cellIlluminated =
          illuminated(row, rowLamps) || illuminated(col, colLamps) || illuminated(col - row,
              dig1Lamps) || illuminated(row + col, dig2Lamps);
      answer[i] = cellIlluminated ? 1 : 0;
      for (int k = 0; k < rowInc.length; k++) {
        int row_ = row + rowInc[k];
        int col_ = col + colInc[k];
        if (!vis.contains(row_ * SHIFT + col_)) {
          continue;
        }
        vis.remove(row_ * SHIFT + col_);
        decrementCount(rowLamps, row_);
        decrementCount(colLamps, col_);
        decrementCount(dig1Lamps, col_ - row_);
        decrementCount(dig2Lamps, row_ + col_);
      }
    }
    return answer;
  }

  private void incrementCount(Map<Integer, Integer> map, int element) {
    map.put(element, map.getOrDefault(element, 0) + 1);
  }


  private void decrementCount(Map<Integer, Integer> map, int element) {
    if (!map.containsKey(element)) {
      return;
    }
    map.put(element, map.get(element) - 1);
  }

  private boolean illuminated(int element, Map<Integer, Integer> map) {
    return map.getOrDefault(element, 0) > 0;
  }


}
