package com.mirak.leetcode.individual.medium;

public class ChampagneTower {

  public double champagneTower(int poured, int query_row, int query_glass) {
    double[][] glasses = new double[100][100];
    glasses[0][0] = poured;
    for (int i = 0; i < glasses.length; ++i) {
      for (int j = 0; j < glasses[i].length; ++j) {
        if (glasses[i][j] > 1) {
          double excess = glasses[i][j] - 1;
          if (i + 1 < glasses.length) {
            glasses[i + 1][j] += excess / 2.0;
            if (j + 1 < glasses[i + 1].length) {
              glasses[i + 1][j + 1] += excess / 2.0;
            }
          }
          glasses[i][j] = 1.0;
        }
      }
    }
    return glasses[query_row][query_glass];
  }
}
