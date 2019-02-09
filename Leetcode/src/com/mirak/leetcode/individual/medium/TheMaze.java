package com.mirak.leetcode.individual.medium;

public class TheMaze {

  private int[] rowInc = {1, -1, 0, 0};
  private int[] columnInc = {0, 0, 1, -1};
  private int[] revDir = {1, 0, 3, 2};

  public boolean hasPath(int[][] maze, int[] start, int[] destination) {
    boolean[][][] vis = new boolean[maze.length][maze[0].length][4];
    for (int k = 0; k < rowInc.length; k++) {
      dfs(start[0], start[1], k, maze, vis);
    }

    int destI = destination[0];
    int destJ = destination[1];
    for (int k = 0; k < rowInc.length; k++) {
      int adjI = destI + rowInc[k];
      int adjJ = destJ + columnInc[k];
      if (isEdge(adjI, adjJ, maze) || !vis[adjI][adjJ][revDir[k]]) {
        continue;
      }
      int revDirI = destI + rowInc[revDir[k]];
      int revDirJ = destJ + columnInc[revDir[k]];
      if (isEdge(revDirI, revDirJ, maze)) {
        return true;
      }
    }
    return false;
  }

  private void dfs(int i, int j, int dir, int[][] maze, boolean[][][] vis) {

    if (isEdge(i, j, maze) || vis[i][j][dir]) {
      return;
    }

    vis[i][j][dir] = true;

    int nextI = i + rowInc[dir];
    int nextJ = j + columnInc[dir];

    if (isEdge(nextI, nextJ, maze)) {
      // choose the next direction.
      for (int k = 0; k < rowInc.length; k++) {
        vis[i][j][k] = true;
        dfs(i + rowInc[k], j + columnInc[k], k, maze, vis);
      }
    } else {
      dfs(nextI, nextJ, dir, maze, vis);
    }

  }

  private boolean isEdge(int i, int j, int[][] maze) {
    return i < 0 || j < 0 || i == maze.length || j == maze[0].length || maze[i][j] == 1;
  }
}
