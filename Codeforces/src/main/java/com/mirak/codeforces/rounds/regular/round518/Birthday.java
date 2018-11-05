package com.mirak.codeforces.rounds.regular.round518;

import java.io.*;

public class Birthday {
  public static void main(String[] args) throws IOException {
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    String[] strs = reader.readLine().split(" ");
    long N = Long.parseLong(strs[0]);
    long M = Long.parseLong(strs[1]);
    long K = Long.parseLong(strs[2]);
    long L = Long.parseLong(strs[3]);

    if(L + K > N) {
      System.out.println(-1);
      return;
    }


    long res = (L + K) / M;
    if ((L + K) % M != 0) {
      res++;
    }

    if (res * M > N || res * M < res) {
      System.out.println(-1);
    } else {
      System.out.println(res);
    }
  }
}
