package com.mirak.codeforces.rounds.regular.mail_ru_cup2018;

import java.io.*;

public class AppendingMex {
  public static void main(String[] args) throws IOException {
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    int n = Integer.parseInt(reader.readLine());
    String[] strs = reader.readLine().split(" ");

    int maxExpected = 0;
    for (int i = 0; i < n; i++) {
      int current = Integer.parseInt(strs[i]);
      if (current <= maxExpected) {
        maxExpected = Math.max(maxExpected, current + 1);
      } else {
        System.out.println(i + 1);
        return;
      }
    }
    System.out.println(-1);
  }
}
