package com.mirak.codeforces.rounds.regular.round530;

import java.io.*;

public class Postcard {

  public static void main(String[] args) throws IOException {
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    String str = reader.readLine();
    int k = Integer.parseInt(reader.readLine());

    int originalLength = getOriginalString(str);

    if (k <= originalLength) {
      while (originalLength > k) {
        int candyIndex = str.indexOf('?');
        int astIndex = str.indexOf('*');
        if (candyIndex == -1 && astIndex == -1) {
          System.out.println("Impossible");
          return;
        }
        if (astIndex != -1) {
          str = str.substring(0, astIndex - 1) + str.substring(astIndex + 1);
        } else {
          str = str.substring(0, candyIndex - 1) + str.substring(candyIndex + 1);
        }
        originalLength--;
      }
      System.out.println(str.replaceAll("\\?", "").replaceAll("\\*", ""));
    } else {
      str = str.replaceAll("\\?", "");
      int astIndex = str.indexOf('*');
      if (astIndex == -1) {
        System.out.println("Impossible");
        return;
      }
      int addedChars = k - originalLength;
      char repeatedChar = str.charAt(astIndex - 1);
      StringBuilder builder = new StringBuilder();
      for (int i = 0; i < addedChars; i++) {
        builder.append(repeatedChar);
      }
      str = str.substring(0, astIndex) + builder.toString() + str.substring(astIndex + 1)
          .replaceAll("\\*", "");
      System.out.println(str);
    }
  }


  private static int getOriginalString(String str) {
    int length = 0;
    for (char a : str.toCharArray()) {
      if (Character.isAlphabetic(a) && Character.isLowerCase(a)) {
        length++;
      }
    }
    return length;
  }
}
