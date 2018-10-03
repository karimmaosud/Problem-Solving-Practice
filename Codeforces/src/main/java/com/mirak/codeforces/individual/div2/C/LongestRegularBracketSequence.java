package com.mirak.codeforces.individual.div2.C;

import java.io.*;

public class LongestRegularBracketSequence {

  public static void main(String[] args) throws IOException {
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    String s = reader.readLine();

    boolean[] vis = new boolean[s.length() + 1];
    int maxLen = 0, frequency = 1, last = 0, open = 0;

    int prevLength = 0;
    for (int i = 0; i < s.length(); i++) {

      if (s.charAt(i) == '(') {
        open++;
      } else {
        open--;
      }
      if (open == 0) {
        int length = i - last + 1;
        int j = i - (length - prevLength) + 1;
        vis[j] = true;
        prevLength = length;
        if (length > maxLen) {
          frequency = 1;
          maxLen = length;
        } else if (length == maxLen) {
          frequency++;
        }
      } else if (open < 0) {
        last = i + 1;
        open = 0;
        prevLength = 0;
      }
    }

    open = 0;
    last = s.length() - 1;
    for (int i = s.length() - 1; i >= 0; i--) {
      if (s.charAt(i) == ')') {
        open++;
      } else {
        open--;
      }
      if (open == 0) {

        if (vis[i]) {
          continue;
        }

        int length = last - i + 1;
        if (length > maxLen) {
          frequency = 1;
          maxLen = length;
        } else if (length == maxLen) {
          frequency++;
        }
      } else if (open < 0) {
        last = i - 1;
        open = 0;
      }
    }

    System.out.println(maxLen + " " + frequency);
  }
}
