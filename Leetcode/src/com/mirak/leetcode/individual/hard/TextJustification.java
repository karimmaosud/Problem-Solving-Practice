package com.mirak.leetcode.individual.hard;

import java.util.*;

public class TextJustification {

  public List<String> fullJustify(String[] words, int maxWidth) {
    List<String> res = new LinkedList<>();
    int i = 0;
    while (i < words.length) {
      int j = i + 1;
      int len = words[i].length();
      while (j < words.length && len + words[j].length() + (j - i) <= maxWidth) {
        len += words[j].length();
        j++;
      }
      j--;
      // pack from i to j.
      if (j == words.length - 1) {
        res.add(packLastLine(words, i, j, maxWidth));
      } else {
        res.add(packWords(words, i, j, len, maxWidth));
      }
      i = j + 1;
    }
    return res;
  }

  private String packWords(String[] words, int i, int j, int len, int maxWidth) {
    StringBuilder builder = new StringBuilder();
    int totalSpaces = maxWidth - len;
    int minSpaces = i != j ? totalSpaces / (j - i) : maxWidth - len;
    int extras = i != j ? totalSpaces % (j - i) : 0;

    builder.append(words[i]);
    appendSpaces(minSpaces, extras, builder);
    extras--;

    for (int k = i + 1; k <= j; k++) {
      if (k != i + 1) {
        appendSpaces(minSpaces, extras, builder);
        extras--;
      }
      builder.append(words[k]);
    }
    return builder.toString();
  }

  private void appendSpaces(int spaces, int extras, StringBuilder builder) {
    for (int i = 0; i < spaces; i++) {
      builder.append(' ');
    }
    if (extras > 0) {
      builder.append(' ');
    }
  }

  private String packLastLine(String[] words, int i, int j, int maxWidth) {
    StringBuilder builder = new StringBuilder();
    builder.append(words[i]);
    int len = words[i].length();
    for (int k = i + 1; k <= j; k++) {
      builder.append(' ');
      builder.append(words[k]);
      len += (1 + words[k].length());
    }
    while (len < maxWidth) {
      builder.append(' ');
      len++;
    }
    return builder.toString();
  }
}
