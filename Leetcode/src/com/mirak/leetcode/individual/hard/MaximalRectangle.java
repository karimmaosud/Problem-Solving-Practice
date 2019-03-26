package com.mirak.leetcode.individual.hard;

import java.util.*;

public class MaximalRectangle {

  public int maximalRectangle(char[][] matrix) {
    int n = matrix.length;
    if (n == 0) {
      return 0;
    }
    int m = matrix[0].length;
    int[] heights = new int[m];
    int res = 0;
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < m; j++) {
        if (matrix[i][j] == '1') {
          heights[j]++;
        } else {
          heights[j] = 0;
        }
      }
      res = Math.max(res, getLargestHistogramRectangle(heights));
    }
    return res;
  }

  private int getLargestHistogramRectangle(int[] heights) {
    Stack<Integer> stack = new Stack<>();
    int res = 0;
    for (int i = 0; i <= heights.length; i++) {
      while (!stack.isEmpty() && popStack(heights, stack.peek(), i)) {
        int idx = stack.pop();
        int width = stack.isEmpty() ? i : i - stack.peek() - 1;
        res = Math.max(res, width * heights[idx]);
      }
      stack.push(i);
    }
    return res;
  }

  private boolean popStack(int[] heights, int stackIndex, int currentIndex) {
    return currentIndex == heights.length || heights[currentIndex] < heights[stackIndex];
  }
}
