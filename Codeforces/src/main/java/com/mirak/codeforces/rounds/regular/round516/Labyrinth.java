package com.mirak.codeforces.rounds.regular.round516;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class Labyrinth {

  private static class Node {

    int leftCount, rightCount;

    int r, c;

    Node(int leftCount, int rightCount, int r, int c) {
      this.leftCount = leftCount;
      this.rightCount = rightCount;
      this.r = r;
      this.c = c;
    }
  }

  public static void main(String[] args) throws IOException {
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    String[] strs = reader.readLine().split(" ");
    int n = Integer.parseInt(strs[0]);
    int m = Integer.parseInt(strs[1]);

    char[][] grid = new char[n][m];
    strs = reader.readLine().split(" ");
    int r = Integer.parseInt(strs[0]) - 1;
    int c = Integer.parseInt(strs[1]) - 1;

    strs = reader.readLine().split(" ");
    int maxLeft = Integer.parseInt(strs[0]);
    int maxRight = Integer.parseInt(strs[1]);

    for (int i = 0; i < n; i++) {
      grid[i] = reader.readLine().toCharArray();
    }

    boolean[][] vis = new boolean[n][m];
    bfs(r, c, vis, grid, maxLeft, maxRight);
    int res = 0;
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < m; j++) {
        if (vis[i][j]) {
          res++;
        }
      }
    }
    System.out.println(res);
  }

  private static void bfs(int startRow, int startColumn, boolean[][] vis, char[][] grid,
      int maxLeft, int maxRight) {

    int n = grid.length;
    int m = grid[0].length;

    PriorityQueue<Node> q = new PriorityQueue<>(new Comparator<Node>() {
      @Override
      public int compare(Node o1, Node o2) {
        int leftCountDiff = o1.leftCount - o2.leftCount;
        return leftCountDiff == 0 ? o1.rightCount - o2.rightCount : leftCountDiff;
      }
    });
    q.add(new Node(0, 0, startRow, startColumn));

    vis[startRow][startColumn] = true;

    while (!q.isEmpty()) {
      Node node = q.poll();

      int r = node.r;
      int c = node.c;
      int currentLeft = node.leftCount;
      int currentRight = node.rightCount;
      // up
      if (isValidCell(r - 1, c, n, m, vis, grid)) {
        vis[r - 1][c] = true;
        q.add(new Node(currentLeft, currentRight, r - 1, c));
      }
      // down
      if (isValidCell(r + 1, c, n, m, vis, grid)) {
        vis[r + 1][c] = true;
        q.add(new Node(currentLeft, currentRight, r + 1, c));
      }

      // right
      if (validRestricted(r, c + 1, n, m, vis, grid, currentRight, maxRight)) {
        vis[r][c + 1] = true;
        q.add(new Node(currentLeft, currentRight + 1, r, c + 1));
      }
      // left
      if (validRestricted(r, c - 1, n, m, vis, grid, currentLeft, maxLeft)) {
        vis[r][c - 1] = true;
        q.add(new Node(currentLeft + 1, currentRight, r, c - 1));
      }
    }
  }

  private static boolean isValidCell(int r, int c, int n, int m, boolean[][] vis, char[][] grid) {
    return r >= 0 && r < n && c >= 0 && c < m && !vis[r][c] && grid[r][c] != '*';
  }

  private static boolean validRestricted(int r, int c, int n, int m, boolean[][] vis, char[][] grid,
      int moved, int bound) {
    return isValidCell(r, c, n, m, vis, grid) && moved + 1 <= bound;
  }

}
