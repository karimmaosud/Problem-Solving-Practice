package com.mirak.google.codejam.cg2019.qualifications;

import java.io.*;

public class ForegoneSolution {

  public static void main(String[] args) throws IOException {
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    int T = Integer.parseInt(reader.readLine());
    for (int t = 1; t <= T; ++t) {
      String num = reader.readLine();
      StringBuilder a = new StringBuilder();
      StringBuilder b = new StringBuilder();
      for (char c : num.toCharArray()) {
        if (c == '4') {
          a.append('2');
          b.append('2');
        } else {
          a.append(c);
          b.append('0');
        }
      }
      int j = 0;
      while (b.charAt(j) == '0') {
        j++;
      }
      System.out.println("Case #" + t + ": " + a + " " + (b.substring(j)));
    }
  }
}
