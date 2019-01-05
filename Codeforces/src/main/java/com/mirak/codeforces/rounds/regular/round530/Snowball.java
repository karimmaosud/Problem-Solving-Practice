package com.mirak.codeforces.rounds.regular.round530;

import java.io.*;

public class Snowball {
  public static void main(String[] args) throws IOException {
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    String[] strs = reader.readLine().split(" ");
    int w = Integer.parseInt(strs[0]);
    int h = Integer.parseInt(strs[1]);
    strs = reader.readLine().split(" ");

    int u1 = Integer.parseInt(strs[0]);
    int d1 = Integer.parseInt(strs[1]);

    strs = reader.readLine().split(" ");
    int u2 = Integer.parseInt(strs[0]);
    int d2 = Integer.parseInt(strs[1]);

    while (h > 0) {
      w += h;
      if (h == d1) {
        w = Math.max(0, w - u1);
      } else if(h == d2) {
        w = Math.max(0, w - u2);
      }
      h--;
    }
    System.out.println(w);
  }
}
