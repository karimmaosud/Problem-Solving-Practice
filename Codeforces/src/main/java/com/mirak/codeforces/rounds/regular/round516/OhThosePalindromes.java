package com.mirak.codeforces.rounds.regular.round516;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class OhThosePalindromes {
  public static void main(String[] args) throws IOException {
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    int n = Integer.parseInt(reader.readLine());
    String str = reader.readLine();
    int[] count = new int[26];
    for (char c: str.toCharArray()) {
      count[c - 'a']++;
    }
    StringBuilder builder = new StringBuilder();
    for (int i = 0; i < count.length; i++) {
      for (int j = 0; j < count[i]; j++) {
        builder.append((char) (i + 'a'));
      }
    }
    System.out.println(builder.toString());
  }
}
