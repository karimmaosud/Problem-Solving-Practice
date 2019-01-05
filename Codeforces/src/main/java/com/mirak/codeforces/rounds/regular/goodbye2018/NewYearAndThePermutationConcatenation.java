package com.mirak.codeforces.rounds.regular.goodbye2018;

import java.io.*;

public class NewYearAndThePermutationConcatenation {

  private static long MOD = 998244353L;

  public static void main(String[] args) throws IOException {
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    int n = Integer.parseInt(reader.readLine());
    if (n == 1) {
      System.out.println(1);
      return;
    }
    if (n == 2) {
      System.out.println(2);
      return;
    }
    if (n == 3) {
      System.out.println(9);
      return;
    }
    long[] factorial = new long[n + 1];
    long[] factor = new long[n + 1];
    long[] res = new long[n + 1];
    factorial[0] = 1;
    factorial[1] = 1;
    factorial[2] = 2;
    factorial[3] = 6;

    factor[3] = 3;
    res[3] = 9;
    res[2] = 2;
    for (int i = 4; i <= n; i++) {
      factor[i] =
          (((i - 1) * ((factor[i - 1] + factorial[i - 2]) % MOD - 1 + MOD) % MOD) % MOD + (i - 2))
              % MOD;
      res[i] = (factor[i] * i % MOD) % MOD;
      factorial[i] = (factorial[i - 1] * i) % MOD;
    }
    System.out.println(res[n]);
  }
}
