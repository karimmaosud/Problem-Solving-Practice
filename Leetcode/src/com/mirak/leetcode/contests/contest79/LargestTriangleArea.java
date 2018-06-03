package com.mirak.leetcode.contests.contest79;

public class LargestTriangleArea {
  public double largestTriangleArea(int[][] points) {
    int n = points.length;
    double res = 0.0;
    for(int i = 0; i < n; i++) {
      for(int j = 0; j < n; j++) {
        for(int k = 0; k < n; k++) {
          if(!isSameLine(i, j, k, points)) {
            double area = 0.5 * Math.abs((points[i][0] - points[k][0]) * (points[j][1] - points[i][1]) - (points[i][0] - points[j][0]) * (points[k][1] - points[i][1]));
            res = Math.max(res, area);
          }
        }
      }
    }
    return res;
  }

  private boolean isSameLine(int i, int j, int k, int[][] points) {
    if(points[i][0] == points[j][0] && points[i][0] == points[k][0]) {
      // same x.
      return true;
    }

    if(points[i][1] == points[j][1] && points[i][1] == points[k][1]) {
      // same y.
      return true;
    }
    return false;
  }
}
