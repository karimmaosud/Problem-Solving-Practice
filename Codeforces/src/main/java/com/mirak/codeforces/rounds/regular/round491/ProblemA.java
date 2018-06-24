package com.mirak.codeforces.rounds.regular.round491;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ProblemA {
  public static void main(String[] args) throws IOException {
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    String[] strs = reader.readLine().split(" ");
    int a = Integer.parseInt(strs[0]);
    int b = Integer.parseInt(strs[1]);
    int c = Integer.parseInt(strs[2]);
    int n = Integer.parseInt(strs[3]);
    if(c > Math.min(a, b) || a + b - c > n) {
      System.out.println(-1);
      return;
    }
    int rem = n - (a + b - c);
    if(rem == 0) {
      System.out.println(-1);
      return;
    }
    System.out.println(rem);
  }
}
