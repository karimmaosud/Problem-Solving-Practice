package com.mirak.codeforces.rounds.regular.round516;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class EquationsOfMathematicalMagic {
  public static void main(String[] args) throws IOException {
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    int T = Integer.parseInt(reader.readLine());
    for (int t = 0; t < T; t++) {
      long n = Long.parseLong(reader.readLine());
      System.out.println((1L << (countOnes(n))));
    }
  }

  private static int countOnes(long n) {
    int ones = 0;
    while (n > 0) {
      n -= (n & -n);
      ones++;
    }
    return ones;
  }
}
