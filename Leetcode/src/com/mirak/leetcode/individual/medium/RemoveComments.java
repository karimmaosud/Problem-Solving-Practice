package com.mirak.leetcode.individual.medium;

import java.util.*;

public class RemoveComments {

  public List<String> removeComments(String[] source) {
    if (source.length == 0) {
      return new LinkedList<>();
    }
    String concatenatedString = getConcatenatedString(source);
    int idx = 0;
    StringBuilder cleanedSource = new StringBuilder();
    while (idx < concatenatedString.length()) {
      if (concatenatedString.startsWith("//", idx)) {
        // skip until \n
        int nextIdx = concatenatedString.indexOf("\n", idx);
        if (nextIdx == -1) {
          idx = concatenatedString.length();
        } else {
          idx = nextIdx;
        }
      } else if (concatenatedString.startsWith("/*", idx)) {
        // skip until */
        idx = concatenatedString.indexOf("*/", idx + 2) + 2;
      } else {
        cleanedSource.append(concatenatedString.charAt(idx++));
      }
    }
    StringBuilder builder = new StringBuilder();
    for (int i = 0; i < cleanedSource.length(); i++) {
      if (cleanedSource.charAt(i) == '\n' && (builder.length() == 0
          || builder.charAt(builder.length() - 1) == '\n')) {
        continue;
      }
      builder.append(cleanedSource.charAt(i));
    }
    return Arrays.asList(builder.toString().split("\n"));
  }

  private String getConcatenatedString(String[] source) {
    StringBuilder builder = new StringBuilder(source[0]);
    for (int i = 1; i < source.length; i++) {
      builder.append('\n').append(source[i]);
    }
    return builder.toString();
  }
}




