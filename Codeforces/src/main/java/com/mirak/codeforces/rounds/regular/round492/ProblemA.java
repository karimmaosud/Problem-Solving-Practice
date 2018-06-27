package com.mirak.codeforces.rounds.regular.round492;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ProblemA {
  public static void main(String[] args) throws IOException {
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    int n = Integer.parseInt(reader.readLine());
    int[] ar = {1, 5, 10, 20, 100};
    int res = 0;
    for(int i = ar.length - 1; i >= 0; i--) {
      res += n / ar[i];
      n -= (n / ar[i]) * ar[i];
    }
    System.out.println(res);
  }
}
