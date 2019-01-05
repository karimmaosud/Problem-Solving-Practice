package com.mirak.codeforces.rounds.regular.round530;

import java.io.*;

public class SquaresAndSegments {
  public static void main(String[] args) throws IOException {
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    int n = Integer.parseInt(reader.readLine());
    int res = Integer.MAX_VALUE;
    for (int i = 1; i * i <= n; i++) {

      int row = n / i;
      int cellPerRow = n / row;
      int rem = n - row * cellPerRow == 0 ? 0 : 1;
      res = Math.min(res, row + cellPerRow + rem);
    }
    System.out.println(res);
  }
}
