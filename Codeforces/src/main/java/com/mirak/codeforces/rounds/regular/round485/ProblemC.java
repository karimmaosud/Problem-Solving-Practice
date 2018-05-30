package com.mirak.codeforces.rounds.regular.round485;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ProblemC {
  private static final long inf = 1000000000000000000L;
  public static void main(String[] args) throws IOException{
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    int n = Integer.parseInt(reader.readLine());
    String[] strs = reader.readLine().split(" ");
    int[] s = new int[n];
    for(int i = 0; i < n; i++) {
      s[i] = Integer.parseInt(strs[i]);
    }
    strs = reader.readLine().split(" ");
    int[] c = new int[n];
    for(int i = 0; i < n; i++) {
      c[i] = Integer.parseInt(strs[i]);
    }

    long[] minCostLeft = new long[n];
    long[] minCostRight = new long[n];
    initWithInf(minCostLeft);
    initWithInf(minCostRight);
    for(int i = 1; i < n; i++) {
      for(int j = 0; j < i; j++) {
        if(s[j] < s[i]) {
          minCostLeft[i] = Math.min(minCostLeft[i], (long) c[i] + c[j]);
        }
      }
    }
    for(int i = n - 1; i >= 0; i--) {
      for(int j = i + 1; j < n; j++) {
        if(s[j] > s[i]) {
          minCostRight[i] = Math.min(minCostRight[i], (long) c[i] + c[j]);
        }
      }
    }
    long ans = inf;
    for(int i = 0; i < n; i++) {
      if(minCostLeft[i] == inf || minCostRight[i] == inf) {
        continue;
      }
      ans = Math.min(ans, minCostLeft[i] + minCostRight[i] - c[i]);
    }
    if(ans == inf) {
      System.out.println(-1);
    }else{
      System.out.println(ans);
    }
  }

  private static void initWithInf(long[] ar) {
    for(int i = 0; i < ar.length; i++) {
      ar[i] = inf;
    }
  }
}
