package com.mirak.leetcode.individual.hard;

public class SmallestRectangleEnclosingBlackPixels {

  private int[] rowInc = {1, -1, 0, 0};
  private int[] colInc = {0, 0, 1, -1};
  private final int INF = 1000000000;

  private class Rectangle {

    int l, r, u, d;

    Rectangle() {
      l = u = Integer.MAX_VALUE;
      r = d = Integer.MIN_VALUE;
    }
  }

  public int minArea(char[][] image, int x, int y) {
    return minAreaBinSearch(image, x, y);
  }

  private int minAreaDFS(char[][] image, int x, int y) {
    if (image.length == 0) {
      return 0;
    }
    Rectangle rec = new Rectangle();
    dfs(x, y, image, image.length, image[0].length, rec);
    return (rec.r - rec.l + 1) * (rec.d - rec.u + 1);
  }

  private void dfs(int i, int j, char[][] image, int n, int m, Rectangle rec) {
    if (i < 0 || j < 0 || i == n || j == m || image[i][j] != '1') {
      return;
    }
    rec.l = Math.min(rec.l, j);
    rec.r = Math.max(rec.r, j);
    rec.u = Math.min(rec.u, i);
    rec.d = Math.max(rec.d, i);
    image[i][j] = '0';
    for (int k = 0; k < rowInc.length; k++) {
      dfs(i + rowInc[k], j + colInc[k], image, n, m, rec);
    }
  }

  public int minAreaBinSearch(char[][] image, int x, int y) {
    int n = image.length;
    if (n == 0) {
      return 0;
    }
    int m = image[0].length;
    int rightColumn = find(y, m - 1, image, true, false);
    int leftColumn = find(0, y, image, false, false);
    int downMostRow = find(x, n - 1, image, true, true);
    int upperMostRow = find(0, x, image, false, true);
    return (rightColumn - leftColumn + 1) * (downMostRow - upperMostRow + 1);
  }

  private int find(int l, int r, char[][] image, boolean upper, boolean rowSearch) {
    if (l > r) {
      return upper ? -1 : INF;
    }
    int mid = l + (r - l) / 2;
    boolean oneSet = hasOne(mid, image, rowSearch);
    if (oneSet) {
      if (upper) {
        return Math.max(mid, find(mid + 1, r, image, upper, rowSearch));
      } else {
        return Math.min(mid, find(l, mid - 1, image, upper, rowSearch));
      }
    }
    if (upper) {
      return find(l, mid - 1, image, upper, rowSearch);
    }
    return find(mid + 1, r, image, upper, rowSearch);
  }

  private boolean hasOne(int idx, char[][] image, boolean rowSearch) {
    if (rowSearch) {
      return rowHasOne(idx, image);
    }
    return columnHasOne(idx, image);
  }

  private boolean columnHasOne(int idx, char[][] image) {
    for (int i = 0; i < image.length; ++i) {
      if (image[i][idx] == '1') {
        return true;
      }
    }
    return false;
  }

  private boolean rowHasOne(int idx, char[][] image) {
    for (int i = 0; i < image[0].length; ++i) {
      if (image[idx][i] == '1') {
        return true;
      }
    }
    return false;
  }
}
