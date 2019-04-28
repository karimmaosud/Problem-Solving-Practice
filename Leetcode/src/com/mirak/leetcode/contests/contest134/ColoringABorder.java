package com.mirak.leetcode.contests.contest134;

public class ColoringABorder {

  private static int[] rowInc = {1, -1, 0, 0};
  private static int[] colInc = {0, 0, 1, -1};

  public static int[][] colorBorder(int[][] grid, int r0, int c0, int color) {
    int[][] newGrid = new int[grid.length][];
    for (int i = 0; i < grid.length; ++i) {
      newGrid[i] = grid[i].clone();
    }
    int componentColor = grid[r0][c0];
    dfs(r0, c0, grid, new boolean[grid.length][grid[0].length], componentColor, color, newGrid);
    return newGrid;
  }

  private static void dfs(int i, int j, int[][] grid, boolean[][] vis, int componentColor,
      int color,
      int[][] newGrid) {
    if (i < 0 || j < 0 || i == grid.length || j == grid[i].length || vis[i][j]
        || grid[i][j] != componentColor) {
      return;
    }
    vis[i][j] = true;
    if (isBorder(i, j, grid, componentColor)) {
      newGrid[i][j] = color;
    }

    for (int k = 0; k < rowInc.length; ++k) {
      dfs(i + rowInc[k], j + colInc[k], grid, vis, componentColor, color, newGrid);
    }

  }

  private static boolean isBorder(int i, int j, int[][] grid, int componentColor) {
    for (int k = 0; k < rowInc.length; ++k) {
      int i_ = i + rowInc[k];
      int j_ = j + colInc[k];
      if (i_ < 0 || j_ < 0 || i_ == grid.length || j_ == grid[i_].length
          || grid[i_][j_] != componentColor) {
        return true;
      }
    }
    return false;
  }


  public static void main(String[] args) {
    System.out.println(
        colorBorder(new int[][]{{1, 2, 1, 2, 1, 2}, {2, 2, 2, 2, 1, 2}, {1, 2, 2, 2, 1, 2}}, 1, 3,
            1));
  }

}
