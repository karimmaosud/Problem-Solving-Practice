package com.mirak.leetcode.individual.hard;

import java.util.*;

public class TheMazeIII {

  private int[] rowInc = {1, 0, 0, -1};
  private int[] colInc = {0, -1, 1, 0};
  private char[] chars = {'d', 'l', 'r', 'u'};

  public String findShortestWay(int[][] maze, int[] ball, int[] hole) {
    return bfs(maze, ball, hole);
  }

  private String bfs(int[][] maze, int[] ball, int[] hole) {
    int n = maze.length;
    int m = maze[0].length;
    boolean[][][] vis = new boolean[n][m][4];
    int[][][][] p = new int[n][m][4][];

    Queue<int[]> q = new LinkedList<>();
    init(vis, p, ball, q, maze);

    while (!q.isEmpty()) {
      int[] state = q.poll();

      int row = state[0];
      int col = state[1];
      int dir = state[2];

      if (row == hole[0] && col == hole[1]) {
        // end with the give direction.
        StringBuilder builder = new StringBuilder();
        appendPath(p, row, col, dir, ball, builder.append(chars[dir]));
        return builder.reverse().toString();
      }

      int nextRow = row + rowInc[dir];
      int nextCol = col + colInc[dir];

      if (isWall(nextRow, nextCol, maze)) {
        for (int k = 0; k < rowInc.length; ++k) {

          nextRow = row + rowInc[k];
          nextCol = col + colInc[k];

          vis[row][col][k] = true;

          if (!isWall(nextRow, nextCol, maze)
              && !vis[nextRow][nextCol][k]) {
            vis[nextRow][nextCol][k] = true;
            q.add(new int[]{nextRow, nextCol, k});
            p[nextRow][nextCol][k] = new int[]{row, col, k};
            p[row][col][k] = new int[]{row, col, dir};
          }
        }
        continue;
      }

      if (!vis[nextRow][nextCol][dir]) {
        vis[nextRow][nextCol][dir] = true;
        p[nextRow][nextCol][dir] = new int[]{row, col, dir};
        q.add(new int[]{nextRow, nextCol, dir});
      }
    }
    return "impossible";
  }


  private void init(boolean[][][] vis, int[][][][] p, int[] ball, Queue<int[]> q,
      int[][] maze) {
    for (int k = 0; k < rowInc.length; ++k) {
      vis[ball[0]][ball[1]][k] = true;

      if (!isWall(ball[0] + rowInc[k], ball[1] + colInc[k], maze)) {
        int nextRow = ball[0] + rowInc[k];
        int nextCol = ball[1] + colInc[k];

        vis[nextRow][nextCol][k] = true;
        p[nextRow][nextCol][k] = new int[]{ball[0], ball[1], k};

        q.add(new int[]{nextRow, nextCol, k});
      }
    }
  }

  private boolean isWall(int row, int col, int[][] maze) {
    return row < 0 || col < 0 || row == maze.length || col == maze[0].length || maze[row][col] == 1;
  }

  private void appendPath(int[][][][] p, int i, int j, int dir, int[] ball,
      StringBuilder builder) {
    if (i == ball[0] && j == ball[1]) {
      return;
    }
    int[] parent = p[i][j][dir];
    if (i == parent[0] && j == parent[1]) {
      // change direction.
      builder.append(chars[parent[2]]);
    }
    appendPath(p, parent[0], parent[1], parent[2], ball, builder);
  }
}
