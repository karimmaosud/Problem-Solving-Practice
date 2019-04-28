package com.mirak.leetcode.contests.contest134;

import java.awt.Point;
import java.util.*;

public class EscapeALargeMaze {

  private int[] rowInc = {1, -1, 0, 0};
  private int[] colInc = {0, 0, 1, -1};
  private int MAXN = 1000000;

  public boolean isEscapePossible(int[][] blocked, int[] source, int[] target) {

    Set<Point> blockedPoints = new HashSet<>();

    for (int[] blockedCell : blocked) {
      blockedPoints.add(new Point(blockedCell[0], blockedCell[1]));
    }

    Point sourcePoint = new Point(source[0], source[1]);
    Point targetPoint = new Point(target[0], target[1]);

    return !surroundedPoint(sourcePoint, blockedPoints) && !surroundedPoint(targetPoint,
        blockedPoints);
  }

  private boolean surroundedPoint(Point point, Set<Point> blockedPoints) {
    Set<Point> vis = new HashSet<>();
    Queue<Point> q = new LinkedList<>();
    q.add(point);
    for (int i = 0; i < 201; ++i) {
      int size = q.size();
      if (size == 0) {
        return true;
      }
      for (int j = 0; j < size; ++j) {
        Point p = q.poll();
        for (int k = 0; k < rowInc.length; ++k) {
          int i_ = p.x + rowInc[k];
          int j_ = p.y + colInc[k];
          if (outOfGridPoint(i_, j_)) {
            continue;
          }
          Point next = new Point(i_, j_);
          if (vis.contains(next) || blockedPoints.contains(next)) {
            continue;
          }
          vis.add(next);
          q.add(next);
        }
      }
    }
    return false;
  }

  private boolean outOfGridPoint(int i, int j) {
    return i < 0 || j < 0 || i == MAXN || j == MAXN;
  }
}
