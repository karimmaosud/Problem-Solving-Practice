package com.mirak.leetcode.individual.hard;

import java.util.*;


public class ShortestPathToGetAllKeys {

  private final int INF = 1000000000;
  private int[] rowInc = {1, -1, 0, 0};
  private int[] colInc = {0, 0, 1, -1};

  public int shortestPathAllKeys(String[] grid) {

    int n = grid.length;
    int m = grid[0].length();

    int[] start = new int[2];

    int keys = getNumKeys(grid, start);

    int[][][] dist = new int[n][m][1 << keys];
    bfs(start, dist, keys, n, m, grid);

    int ans = INF;
    for (int i = 0; i < n; ++i) {
      for (int j = 0; j < m; ++j) {
        if (Character.isLowerCase(grid[i].charAt(j))) {
          ans = Math.min(ans, dist[i][j][(1 << keys) - 1]);
        }
      }
    }
    return ans == INF ? -1 : ans;
  }

  private void bfs(int[] start, int[][][] dist, int keys, int n, int m, String[] grid) {
    for (int[][] row : dist) {
      for (int[] ar : row) {
        Arrays.fill(ar, INF);
      }
    }
    dist[start[0]][start[1]][0] = 0;
    Queue<int[]> q = new LinkedList<>();
    q.add(new int[]{start[0], start[1], 0});
    while (!q.isEmpty()) {
      int[] state = q.poll();
      int r = state[0];
      int c = state[1];
      int mask = state[2];
      if (mask == (1 << keys) - 1) {
        continue;
      }
      for (int k = 0; k < rowInc.length; ++k) {
        int r_ = r + rowInc[k];
        int c_ = c + colInc[k];
        if (r_ < 0 || c_ < 0 || r_ == n || c_ == m || grid[r_].charAt(c_) == '#') {
          continue;
        }

        if (isLock(grid[r_].charAt(c_)) && !haveKey(mask, grid[r_].charAt(c_))) {
          continue;
        }

        int nextMask = mask;
        if (isKey(grid[r_].charAt(c_))) {
          nextMask |= (1 << getBitIndex(grid[r_].charAt(c_)));
        }

        if (dist[r_][c_][nextMask] != INF) {
          continue;
        }

        dist[r_][c_][nextMask] = dist[r][c][mask] + 1;
        q.add(new int[]{r_, c_, nextMask});
      }
    }

  }

  private int getNumKeys(String[] grid, int[] start) {
    int count = 0;
    for (int i = 0; i < grid.length; ++i) {
      for (int j = 0; j < grid[i].length(); ++j) {
        if (Character.isLowerCase(grid[i].charAt(j))) {
          count++;
        } else if (grid[i].charAt(j) == '@') {
          start[0] = i;
          start[1] = j;
        }
      }
    }
    return count;
  }

  private boolean haveKey(int mask, char lock) {
    int bitIndex = getBitIndex(lock);
    return (mask & (1 << bitIndex)) != 0;
  }

  private int getBitIndex(char a) {
    return isKey(a) ? a - 'a' : a - 'A';
  }

  private boolean isLock(char a) {
    return Character.isUpperCase(a);
  }

  private boolean isKey(char a) {
    return Character.isLowerCase(a);
  }
}
