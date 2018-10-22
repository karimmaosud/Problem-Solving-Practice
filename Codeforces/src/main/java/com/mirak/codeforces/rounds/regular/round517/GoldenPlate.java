package com.mirak.codeforces.rounds.regular.round517;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class GoldenPlate {
  public static void main(String[] args) throws IOException {
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    String[] strs = reader.readLine().split(" ");
    int w = Integer.parseInt(strs[0]);
    int h = Integer.parseInt(strs[1]);
    int k = Integer.parseInt(strs[2]);

    int res = 0;
    for (int i = 0; i < k; i++) {
      res += ((2 * w) + (2 * h) - 4);
      w -= 4;
      h -= 4;
    }
    System.out.println(res);
  }
}
