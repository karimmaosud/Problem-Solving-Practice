package com.mirak.leetcode.contests.contest112;

import java.awt.Point;
import java.util.*;

public class MostStonesRemovedWithSameRowOrColumn {

  public int removeStones(int[][] stones) {
    Map<Integer, ArrayList<Point>> rowStones = new HashMap<>();
    Map<Integer, ArrayList<Point>> columnStones = new HashMap<>();

    for (int i = 0; i < stones.length; i++) {
      Point p = new Point(stones[i][0], stones[i][1]);
      rowStones.putIfAbsent(stones[i][0], new ArrayList<>());
      columnStones.putIfAbsent(stones[i][1], new ArrayList<>());
      rowStones.get(stones[i][0]).add(p);
      columnStones.get(stones[i][1]).add(p);
    }

    Set<Point> vis = new HashSet<>();

    int res = 0;

    for (int i = 0; i <= 10000; i++) {
      if (rowStones.containsKey(i)) {
        for (Point p : rowStones.get(i)) {
          if (!vis.contains(p)) {
            res += dfs(p, rowStones, columnStones, vis) - 1;
          }
        }
      }
    }

    return res;
  }

  private int dfs(Point p, Map<Integer, ArrayList<Point>> rowStones,
      Map<Integer, ArrayList<Point>> columnStones, Set<Point> vis) {

    if (vis.contains(p)) {
      return 0;
    }

    vis.add(p);

    int ans = 1;
    for (Point next : rowStones.get(p.x)) {
      ans += dfs(next, rowStones, columnStones, vis);
    }

    for (Point next : columnStones.get(p.y)) {
      ans += dfs(next, rowStones, columnStones, vis);
    }
    return ans;
  }
}
