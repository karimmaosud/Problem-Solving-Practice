package com.mirak.leetcode.individual.medium;

import java.util.*;

public class ReorganizeString {

  public String reorganizeString(String S) {
    int n = S.length();
    if (n == 0) {
      return "";
    }
    int[] charCount = new int[26];
    for (char a : S.toCharArray()) {
      charCount[a - 'a']++;
    }
    int[] lastIndex = new int[26];
    Arrays.fill(lastIndex, -1000);
    char[] res = new char[n];
    Arrays.fill(res, '\0');
    for (int i = 0; i < n; i++) {
      int maxOccurrence = 0;
      int charNum = -1;
      for (int j = 0; j < charCount.length; j++) {
        if (charCount[j] > maxOccurrence && i - lastIndex[j] >= 2) {
          maxOccurrence = charCount[j];
          charNum = j;
        }
      }
      if (charNum == -1) {
        return "";
      }
      lastIndex[charNum] = i;

      res[lastIndex[charNum]] = (char) (charNum + 'a');
      charCount[charNum]--;
    }
    return new String(res);
  }
}
