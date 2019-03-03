package com.mirak.leetcode.contests.contest126;

import java.util.*;

public class FindCommonCharacters {
  private final int INF = 1000000000;
  public List<String> commonChars(String[] A) {
    int[] charOccurrence = new int[26];
    Arrays.fill(charOccurrence, INF);
    for (String str : A) {
      int[] localOccurrence = new int[26];
      for (char a : str.toCharArray()) {
        localOccurrence[a - 'a']++;
      }
      for (int i = 0; i < 26; i++) {
        charOccurrence[i] = Math.min(charOccurrence[i], localOccurrence[i]);
      }
    }
    List<String> res = new LinkedList<>();
    for (int i = 0; i < 26; i++) {
      if (charOccurrence[i] == INF) {
        continue;
      }
      for (int j = 0; j < charOccurrence[i]; j++) {
        res.add("" + (char) (i + 'a'));
      }
    }
    return res;
  }

}
