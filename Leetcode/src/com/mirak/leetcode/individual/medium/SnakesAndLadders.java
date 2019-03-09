package com.mirak.leetcode.individual.medium;

import java.util.*;

public class SnakesAndLadders {

  private final int INF = 1000000000;

  public int snakesAndLadders(int[][] board) {
    return bfs(board);
  }

  private int bfs(int[][] board) {
    int n = board.length;
    int start = 1;
    int[] dist = new int[n * n + 1];
    Arrays.fill(dist, INF);
    dist[start] = 0;
    Queue<Integer> q = new LinkedList<>();
    q.add(start);
    while (!q.isEmpty()) {
      int top = q.poll();
      for (int i = 1; i <= 6; i++) {
        if (top + i > n * n) {
          break;
        }
        int cell = top + i;
        int row = getRow(cell, n);
        int col = getCol(cell, row, n);
        if (board[row][col] != -1) {
          cell = board[row][col];
        }
        if (dist[cell] == INF) {
          dist[cell] = dist[top] + 1;
          q.add(cell);
        }
      }
    }
    return dist[n * n] == INF ? -1 : dist[n * n];
  }


  private int getRow(int cell, int n) {
    return n - ((cell - 1) / n) - 1;
  }

  private int getCol(int cell, int row, int n) {
    int col = (cell - 1) % n;
    return ((n - row) & 1) == 0 ? n - col - 1 : col;
  }
}
