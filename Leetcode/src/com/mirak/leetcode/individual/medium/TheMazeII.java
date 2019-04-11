package com.mirak.leetcode.individual.medium;

import java.util.*;

public class TheMazeII {

  private int[] rowInc = {1, -1, 0, 0};
  private int[] colInc = {0, 0, 1, -1};
  private final int SHIFT = 7;

  public int shortestDistance(int[][] maze, int[] start, int[] destination) {
    return bfs(maze, start, destination);
  }

  private int bfs(int[][] maze, int[] start, int[] destination) {
    Map<Integer, Integer> dist = new HashMap<>();
    dist.put(getMask(start[0], start[1]), 0);
    Queue<Integer> q = new LinkedList<>();
    q.add(getMask(start[0], start[1]));
    while (!q.isEmpty()) {
      int mask = q.poll();
      int row = getRow(mask);
      int col = getColumn(mask);
      for (int i = 0; i < rowInc.length; ++i) {
        int endRow = row, endColumn = col;
        int steps = 0;
        while (!isWall(endRow + rowInc[i], endColumn + colInc[i], maze)) {
          endRow += rowInc[i];
          endColumn += colInc[i];
          steps++;
        }
        int endMask = getMask(endRow, endColumn);
        if (!dist.containsKey(endMask) || dist.get(endMask) > dist.get(mask) + steps) {
          dist.put(endMask, dist.get(mask) + steps);
          q.add(endMask);
        }
      }
    }
    return dist.getOrDefault(getMask(destination[0], destination[1]), -1);
  }

  private int getRow(int mask) {
    return mask >> SHIFT;
  }

  private int getColumn(int mask) {
    return mask & ((1 << SHIFT) - 1);
  }

  private int getMask(int row, int column) {
    return (row << SHIFT) | column;
  }

  private boolean isWall(int i, int j, int[][] maze) {
    return i < 0 || j < 0 || i == maze.length || j == maze[0].length || maze[i][j] == 1;
  }

}
