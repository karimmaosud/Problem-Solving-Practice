package com.mirak.codeforces.individual.div2.C;

import java.io.*;

public class AlyonaAndSpreadsheet {

  public static void main(String[] args) throws IOException {
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    String[] strs = reader.readLine().split(" ");

    int n = getIntegerValue(strs, 0);
    int m = getIntegerValue(strs, 1);
    int[][] grid = new int[n][m];

    for (int i = 0; i < n; i++) {
      strs = reader.readLine().split(" ");
      for (int j = 0; j < m; j++) {
        grid[i][j] = getIntegerValue(strs, j);
      }
    }

    int[] rowMax = new int[n];
    for (int j = 0; j < m; j++) {
      int i = 0;
      while (i < n) {
        int k = i + 1;
        while (k < n && grid[k - 1][j] <= grid[k][j]) {
          k++;
        }
        for (int l = i; l < k; l++) {
          rowMax[l] = Math.max(rowMax[l], k - 1);
        }
        i = k;
      }
    }

    int K = Integer.parseInt(reader.readLine());
    for (int k = 0; k < K; k++) {
      strs = reader.readLine().split(" ");
      int upperRow = getIntegerValue(strs, 0) - 1;
      int lowerRow = getIntegerValue(strs, 1) - 1;
      if (rowMax[upperRow] >= lowerRow) {
        System.out.println("Yes");
      } else {
        System.out.println("No");
      }
    }

  }

  private static int getIntegerValue(String[] strs, int index) {
    return Integer.parseInt(strs[index]);
  }

}
