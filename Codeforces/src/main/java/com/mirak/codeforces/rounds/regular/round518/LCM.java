package com.mirak.codeforces.rounds.regular.round518;

import java.io.*;

public class LCM {
  public static void main(String[] args) throws IOException {
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    long b = Long.parseLong(reader.readLine());
    int res = 0;
    for (long i = 1; i * i <= b; i++) {
      if (b % i == 0) {
        long n1 = i;
        long n2 = b / i;
        if (n1 == n2) {
          res++;
        } else {
          res += 2;
        }
      }
    }
    System.out.println(res);
  }
}
