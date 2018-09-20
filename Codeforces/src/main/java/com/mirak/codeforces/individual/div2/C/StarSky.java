package com.mirak.codeforces.individual.div2.C;

import java.io.*;

public class StarSky {

  public static void main(String[] args) throws IOException {
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    String[] strs = reader.readLine().split(" ");
    int n = Integer.parseInt(strs[0]);
    int q = Integer.parseInt(strs[1]);
    int c = Integer.parseInt(strs[2]);

    int[][][] count = new int[11][101][101];

    for (int i = 0; i < n; i++) {
      strs = reader.readLine().split(" ");
      int x = Integer.parseInt(strs[0]);
      int y = Integer.parseInt(strs[1]);
      int s = Integer.parseInt(strs[2]);
      count[s][x][y]++;
    }

    int[][][] sum = new int[11][101][101];
    for (int i = 0; i < sum.length; i++) {
      for (int j = 1; j < sum[i].length; j++) {
        for (int k = 1; k < sum[i][j].length; k++) {
          sum[i][j][k] =
              count[i][j][k] + sum[i][j - 1][k] + sum[i][j][k - 1] - sum[i][j - 1][k - 1];
        }
      }
    }

    for (int qNum = 0; qNum < q; qNum++) {
      strs = reader.readLine().split(" ");
      int ti = Integer.parseInt(strs[0]);
      int x1 = Integer.parseInt(strs[1]);
      int y1 = Integer.parseInt(strs[2]);
      int x2 = Integer.parseInt(strs[3]);
      int y2 = Integer.parseInt(strs[4]);
      System.out.println(getRectangleSum(x1, y1, x2, y2, sum, ti, c));
    }
  }


  private static int getRectangleSum(int x1, int y1, int x2, int y2, int[][][] sum, int t, int c) {
    int res = 0;
    for (int s = 0; s <= 10; s++) {
      int brightness = (s + t) % (c + 1);
      int count = sum[s][x2][y2] - sum[s][x2][y1 - 1] - sum[s][x1 - 1][y2] + sum[s][x1 - 1][y1 - 1];
      res += count * brightness;
    }
    return res;
  }
}
