package com.mirak.codeforces.rounds.regular.round554;

import java.io.*;

public class NekoFindsGrapes {

  public static void main(String[] args) throws IOException {
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    String[] strs = reader.readLine().split(" ");
    int n = Integer.parseInt(strs[0]);
    int m = Integer.parseInt(strs[1]);
    int[] chests = new int[n];
    int[] keys = new int[m];
    strs = reader.readLine().split(" ");
    for (int i = 0; i < n; ++i) {
      chests[i] = Integer.parseInt(strs[i]);
    }
    strs = reader.readLine().split(" ");
    for (int i = 0; i < m; ++i) {
      keys[i] = Integer.parseInt(strs[i]);
    }
    int oddKeys = 0;
    for (int key : keys) {
      if ((key & 1) == 1) {
        oddKeys++;
      }
    }
    int evenKeys = m - oddKeys;
    int res = 0;
    for (int chest : chests) {
      if ((chest & 1) == 1 && evenKeys > 0) {
        evenKeys--;
        res++;
      }
      if ((chest & 1) == 0 && oddKeys > 0) {
        oddKeys--;
        res++;
      }
    }
    System.out.println(res);
  }

}
