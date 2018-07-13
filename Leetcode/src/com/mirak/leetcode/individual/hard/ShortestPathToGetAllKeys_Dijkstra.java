package com.mirak.leetcode.individual.hard;

import java.util.*;

public class ShortestPathToGetAllKeys_Dijkstra {

  private int[] rowInc = {1, -1, 0, 0};
  private int[] colInc = {0, 0, 1, -1};
  private int inf = 1000000000;

  private class PQObject {

    int location;
    int mask;
    int dist;

    PQObject(int location, int mask, int dist) {
      this.location = location;
      this.mask = mask;
      this.dist = dist;
    }
  }

  public int shortestPathAllKeys(String[] grid) {
    for (String row : grid) {
      System.out.println(row);
    }
    int n = grid.length;
    if (n == 0) {
      return 0;
    }

    int m = grid[0].length();

    Map<Character, Integer> charIndex = new HashMap<>();

    int initialMask = (1 << 6) - 1;
    Set<Character> chars = new HashSet<>();
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < m; j++) {
        if (grid[i].charAt(j) == '@' || Character.isAlphabetic(grid[i].charAt(j))) {
          charIndex.put(grid[i].charAt(j), i | (j << 10));
          chars.add(grid[i].charAt(j));
        }

        if (Character.isUpperCase(grid[i].charAt(j))) {
          initialMask ^= (1 << (grid[i].charAt(j) - 'A'));
        }
      }
    }

    int[][] simpleDist = new int[256][256];
    int[][] dist = new int[256][1 << 6];
    initDist(simpleDist);
    initDist(dist);

    // 1. pre-calculate distances
    for (char c : chars) {
      bfs(charIndex.get(c), c, grid, simpleDist);
    }

    // run dijkstra starting from @
    PriorityQueue<PQObject> pq = new PriorityQueue<>(new Comparator<PQObject>() {
      @Override
      public int compare(PQObject o1, PQObject o2) {
        return o1.dist - o2.dist;
      }
    });

    dist['@'][initialMask] = 0;
    pq.add(new PQObject(charIndex.get('@'), initialMask, 0));
    while (!pq.isEmpty()) {
      PQObject top = pq.poll();
      int location = top.location;
      int mask = top.mask;
      int d = top.dist;
      char currentChar = grid[location & ((1 << 10) - 1)].charAt(location >> 10);
      if (dist[currentChar][mask] != d) {
        continue;
      }
      for (char c : chars) {
        if (Character.isUpperCase(c) && (mask & (1 << (c - 'A'))) == 0) {
          continue;
        }
        int nextDist = simpleDist[currentChar][c];
        int nextMask = mask;
        if (Character.isLowerCase(c)) {
          nextMask |= (1 << (c - 'a'));
        }
        if (d + nextDist < dist[c][nextMask]) {
          dist[c][nextMask] = d + nextDist;
          pq.add(new PQObject(charIndex.get(c), nextMask, d + nextDist));
        }
      }
    }
    int res = inf;
    for (char a = 'a'; a <= 'f'; a++) {
      res = Math.min(res, dist[a][(1 << 6) - 1]);
    }

    return res == inf ? -1 : res;
  }

  private void initDist(int[][] dist) {
    for (int i = 0; i < dist.length; i++) {
      for (int j = 0; j < dist[i].length; j++) {
        dist[i][j] = inf;
      }
    }
  }

  private void bfs(int initialLocation, char startChar, String[] grid, int[][] simpleDist) {
    int n = grid.length;
    int m = grid[0].length();

    Queue<Integer> q = new LinkedList<>();
    q.add(initialLocation);

    simpleDist[startChar][startChar] = 0;
    int[] dist = new int[1 << 17];
    for (int i = 0; i < dist.length; i++) {
      dist[i] = inf;
    }
    dist[initialLocation] = 0;
    while (!q.isEmpty()) {
      int location = q.poll();
      int row = location & ((1 << 10) - 1);
      int col = location >> 10;
      for (int k = 0; k < 4; k++) {
        int row_ = row + rowInc[k];
        int col_ = col + colInc[k];
        if (row_ < 0 || row_ == n || col_ < 0 || col_ == m || grid[row_].charAt(col_) == '#') {
          continue;
        }

        int nextLocation = row_ | (col_ << 10);
        if (dist[nextLocation] != inf) {
          continue;
        }

        dist[nextLocation] = dist[location] + 1;

        char c = grid[row_].charAt(col_);
        if (c != '.') {
          // c is not '#' neither '.'. So, it's either an alphabetic or '@'.
          simpleDist[startChar][c] = dist[location] + 1;
        }

        if (c == '.') {
          // push to queue iff c is a '.'. If c is alphabetic, stop.
          q.add(nextLocation);
        }
      }
    }
  }
}
