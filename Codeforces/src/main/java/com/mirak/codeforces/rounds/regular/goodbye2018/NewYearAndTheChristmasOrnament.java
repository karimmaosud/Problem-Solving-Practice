package com.mirak.codeforces.rounds.regular.goodbye2018;

import java.io.*;

public class NewYearAndTheChristmasOrnament {

  public static void main(String[] args) throws IOException {
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    String[] strs = reader.readLine().split(" ");
    int y = Integer.parseInt(strs[0]);
    int b = Integer.parseInt(strs[1]);
    int r = Integer.parseInt(strs[2]);

    int max = 0;

    for (int i = 1; i <= y; i++) {
      int j = i + 1;
      int k = i + 2;
      if (j <= b && k <= r && i + j + k > max) {
        max = i + j + k;
      }
    }
    System.out.println(max);
  }
}
