package com.mirak.google.codejam.cg2019.qualifications;

import java.io.*;
import java.util.*;

public class YouCanGoYourOwnWay {

  public static void main(String[] args) throws IOException {
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    int T = Integer.parseInt(reader.readLine());
    for (int t = 1; t <= T; ++t) {
      int n = Integer.parseInt(reader.readLine());
      String s = reader.readLine();
      System.out.println("Case #" + t + ": " + solve(s, n));
    }
  }

  private static String solve(String s, int n) {
    boolean[] rowEntry = new boolean[n];
    boolean[] colEntry = new boolean[n];
    Arrays.fill(rowEntry, true);
    Arrays.fill(colEntry, true);
    int r = 0;
    for (int i = 0; i < s.length(); ++i) {
      int j = i - r;
      if (s.charAt(i) == 'S') {
        colEntry[j] = false;
        r++;
      } else {
        rowEntry[r] = false;
      }
    }
    StringBuilder builder = new StringBuilder();
    int i = s.charAt(0) == 'S' ? 0 : 1;
    int j = i ^ 1;
    builder.append(s.charAt(0) == 'S' ? 'E' : 'S');

    boolean enterFromCol = s.charAt(s.length() - 1) == 'E';

    if (enterFromCol) {
      while (!rowEntry[i]) {
        builder.append('S');
        i++;
      }
      while (j != n - 1) {
        builder.append('E');
        j++;
      }
    } else {
      while (!colEntry[j]) {
        builder.append('E');
        j++;
      }
      while (i != n - 1) {
        builder.append('S');
        i++;
      }
    }
    while (i != n - 1 || j != n - 1) {
      if (i < n - 1) {
        builder.append('S');
        i++;
      } else {
        builder.append('E');
        j++;
      }
    }
    return builder.toString();
  }

}
