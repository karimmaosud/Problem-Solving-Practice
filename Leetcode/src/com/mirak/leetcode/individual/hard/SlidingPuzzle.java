package com.mirak.leetcode.individual.hard;

import java.util.*;

public class SlidingPuzzle {

  private final int INF = 1000000000;
  private int[] rowInc = {1, -1, 0, 0};
  private int[] colInc = {0, 0, 1, -1};

  public int slidingPuzzle(int[][] board) {
    return bfs(board);
  }

  private int bfs(int[][] board) {

    int[] dist = new int[1 << 18];
    int[] zeroIndex = new int[2];
    Arrays.fill(dist, INF);

    int start = encode(board);
    dist[start] = 0;
    Queue<Integer> q = new LinkedList<>();
    q.add(start);

    while (!q.isEmpty()) {
      int top = q.poll();
      if (destinationReached(top)) {
        return dist[top];
      }
      decode(top, board);
      findZeroIndex(top, zeroIndex);
      for (int k = 0; k < rowInc.length; ++k) {
        int i_ = zeroIndex[0] + rowInc[k];
        int j_ = zeroIndex[1] + colInc[k];
        if (i_ < 0 || j_ < 0 || i_ == board.length || j_ == board[i_].length) {
          continue;
        }
        swap(board, zeroIndex[0], zeroIndex[1], i_, j_);
        int nextMask = encode(board);
        if (dist[nextMask] == INF) {
          dist[nextMask] = dist[top] + 1;
          q.add(nextMask);
        }
        swap(board, zeroIndex[0], zeroIndex[1], i_, j_);
      }
    }
    return -1;
  }


  private boolean destinationReached(int mask) {
    for (int i = 0; i < 5; ++i) {
      int num = mask & ((1 << 3) - 1);
      if (num != i + 1) {
        return false;
      }
      mask >>= 3;
    }
    return true;
  }

  private void swap(int[][] board, int i1, int j1, int i2, int j2) {
    int temp = board[i1][j1];
    board[i1][j1] = board[i2][j2];
    board[i2][j2] = temp;
  }

  private void findZeroIndex(int mask, int[] zeroIndex) {
    for (int i = 0; i < 6; ++i) {
      int num = mask & ((1 << 3) - 1);
      if (num == 0) {
        zeroIndex[0] = i / 3;
        zeroIndex[1] = i % 3;
        return;
      }
      mask >>= 3;
    }
  }

  // decodes the mask into the given board
  private void decode(int mask, int[][] board) {
    int m = board[0].length;
    for (int i = 0; i < 6; ++i) {
      board[i / m][i % m] = mask & ((1 << 3) - 1);
      mask >>= 3;
    }
  }

  private int encode(int[][] board) {
    int mask = 0;
    for (int i = board.length - 1; i >= 0; --i) {
      for (int j = board[i].length - 1; j >= 0; --j) {
        mask = (mask << 3) | board[i][j];
      }
    }
    return mask;
  }


}
