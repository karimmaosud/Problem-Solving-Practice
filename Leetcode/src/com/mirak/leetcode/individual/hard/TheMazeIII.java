package com.mirak.leetcode.individual.hard;

import java.util.*;

public class TheMazeIII {

  private int[] rowInc = {1, 0, 0, -1};
  private int[] colInc = {0, -1, 1, 0};
  private char[] chars = {'d', 'l', 'r', 'u'};


  public String findShortestWay(int[][] maze, int[] ball, int[] hole) {
    return bfs(maze, ball, hole, maze.length, maze[0].length);
  }


  private String bfs(int[][] maze, int[] ball, int[] hole, int n, int m) {

    boolean[][][] vis = new boolean[n][m][4];
    int[][][][] path = new int[n][m][4][3];

    Queue<int[]> q = new LinkedList<>();

    int[] start = {ball[0], ball[1], 0};

    setAllDirections(start, vis);

    for (int i = 0; i < rowInc.length; i++) {
      start[2] = i;
      if (isEdgeCell(start, maze, n, m)) {
        continue;
      }
      int[] next = getNextCell(start, maze);
      path[next[0]][next[1]][next[2]] = new int[]{start[0], start[1], start[2]};
      vis[next[0]][next[1]][next[2]] = true;
      q.add(next);
    }
    while (!q.isEmpty()) {

      int[] current = q.poll();

      if (current[0] == hole[0] && current[1] == hole[1]) {
        StringBuilder builder = new StringBuilder();
        builder.append(chars[current[2]]);
        getPath(current, path, ball, builder);
        return builder.reverse().toString();
      }

      if (!isEdgeCell(current, maze, n, m)) {
        int[] next = getNextCell(current, maze);
        if (!vis[next[0]][next[1]][next[2]]) {
          path[next[0]][next[1]][next[2]] = new int[]{current[0], current[1], current[2]};
          q.add(next);
          vis[next[0]][next[1]][next[2]] = true;
        }
        continue;
      }

      setAllDirections(current, vis);

      int prevDirection = current[2];

      for (int i = 0; i < rowInc.length; i++) {
        current[2] = i;
        if (isEdgeCell(current, maze, n, m)) {
          continue;
        }
        int[] next = getNextCell(current, maze);
        if (!vis[next[0]][next[1]][next[2]]) {
          path[current[0]][current[1]][current[2]] = new int[]{current[0], current[1],
              prevDirection};
          path[next[0]][next[1]][next[2]] = new int[]{current[0], current[1], current[2]};

          vis[next[0]][next[1]][next[2]] = true;
          q.add(next);
        }
      }
    }
    return "impossible";
  }

  private void getPath(int[] current, int[][][][] path, int[] ball, StringBuilder builder) {
    if (current[0] == ball[0] && current[1] == ball[1]) {
      return;
    }
    int[] parent = path[current[0]][current[1]][current[2]];
    if (parent[0] == current[0] && parent[1] == current[1]) {
      builder.append(chars[parent[2]]);
    }
    getPath(parent, path, ball, builder);
  }

  private void setAllDirections(int[] cell, boolean[][][] vis) {
    for (int i = 0; i < rowInc.length; i++) {
      vis[cell[0]][cell[1]][i] = true;
    }
  }

  // Edge cell is the cell that can’t move in the given direction.
  private boolean isEdgeCell(int[] cell, int[][] maze, int n, int m) {
    int[] next = {cell[0] + rowInc[cell[2]], cell[1] + colInc[cell[2]]};
    return next[0] < 0 || next[1] < 0 || next[0] == n || next[1] == m
        || maze[next[0]][next[1]] != 0;
  }

  private int[] getNextCell(int[] cell, int[][] maze) {
    return new int[]{cell[0] + rowInc[cell[2]], cell[1] + colInc[cell[2]], cell[2]};
  }

}
