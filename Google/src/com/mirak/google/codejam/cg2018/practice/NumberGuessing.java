package com.mirak.google.codejam.cg2018.practice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class NumberGuessing {
  public static void main(String[] args) throws IOException {
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    int T = Integer.parseInt(reader.readLine());
    String[] strs;
    for(int t = 1; t <= T; t++) {
      strs = reader.readLine().split(" ");
      int a = Integer.parseInt(strs[0]);
      int b = Integer.parseInt(strs[1]);
      reader.readLine();
      guess(a + 1, b, reader);
    }
    reader.close();
  }
  private static void guess(int low, int high, BufferedReader reader) throws IOException {
    while(low <= high) {
      int mid = (low + high) / 2;
      System.out.println(mid);
      System.out.flush();
      String res = reader.readLine();
      if(res.charAt(0) == 'C') {
        // CORRECT
        return;
      }
      if(res.charAt(4) == 'S') {
        // TOO_SMALL
        low = mid + 1;
      }else{
        // TOO_BIG
        high = mid - 1;
      }
    }
  }
}
