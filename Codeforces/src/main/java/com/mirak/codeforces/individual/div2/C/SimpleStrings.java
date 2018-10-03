package com.mirak.codeforces.individual.div2.C;

import java.io.*;

public class SimpleStrings {

  public static void main(String[] args) throws IOException {
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    char[] chars = reader.readLine().toCharArray();
    for (int i = 1; i < chars.length; i++) {
      if (chars[i] == chars[i - 1]) {
        chars[i] = changeCharAtIndex(i, chars);
        i++;
      }
    }
    System.out.println(new String(chars));
  }

  private static char changeCharAtIndex(int i, char[] chars) {
    for (char a = 'a'; a <= 'z'; a++) {
      if (a != chars[i - 1] && (i + 1 == chars.length || a != chars[i + 1])) {
        return a;
      }
    }
    return 'z';
  }
}
