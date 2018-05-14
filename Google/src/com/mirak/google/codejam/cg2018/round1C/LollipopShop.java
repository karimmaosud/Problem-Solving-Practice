package com.mirak.google.codejam.cg2018.round1C;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class LollipopShop {
  public static void main(String[] args) throws IOException {

    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    int T = Integer.parseInt(reader.readLine());
    String[] strs;
    for(int t = 1; t <= T; t++) {
      int n = Integer.parseInt(reader.readLine());
      int[] count = new int[n];
      for(int i = 0; i < n; i++) {
        strs = reader.readLine().split(" ");
        int d = Integer.parseInt(strs[0]);
        int[] preferences = new int[d];
        for(int j = 0; j < d; j++) {
          preferences[j] = Integer.parseInt(strs[j + 1]);
        }
        if(d == 0){
          System.out.println();
        }

      }
    }
  }
}
