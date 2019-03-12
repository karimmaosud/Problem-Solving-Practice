package com.mirak.leetcode.individual.hard;

public class LargestRectangleInHistogram {

  private final int INF = 1000000000;

  public int largestRectangleArea(int[] heights) {
    return solve(0, heights.length - 1, heights);
  }

  private int solve(int left, int right, int[] heights) {
    if (left > right) {
      return 0;
    }
    if (left == right) {
      return heights[left];
    }

    int mid = (right + left) / 2;
    int largestLeft = solve(left, mid, heights);
    int largestRight = solve(mid + 1, right, heights);

    int minH = Math.min(heights[mid], heights[mid + 1]);
    int minW = 2;
    int maxFromBothSides = minH * minW;
    int i = mid - 1, j = mid + 2;

    while (i >= left || j <= right) {
      int runnerRightHeight = getHeight(heights, j, left, right);
      int runnerLeftHeight = getHeight(heights, i, left, right);

      if (runnerLeftHeight < runnerRightHeight) {
        minH = Math.min(minH, runnerRightHeight);
        j++;
      } else {
        minH = Math.min(minH, runnerLeftHeight);
        i--;
      }

      minW++;

      maxFromBothSides = Math.max(maxFromBothSides, minH * minW);
    }
    return Math.max(Math.max(largestLeft, largestRight), maxFromBothSides);
  }

  private int getHeight(int[] heights, int index, int left, int right) {
    return index < left || index > right ? -INF : heights[index];
  }
}
