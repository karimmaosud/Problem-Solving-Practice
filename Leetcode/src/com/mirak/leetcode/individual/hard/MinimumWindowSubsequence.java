package com.mirak.leetcode.individual.hard;

import java.util.ArrayList;

public class MinimumWindowSubsequence {

  public String minWindow(String S, String T) {
    ArrayList<Integer>[] charIndexes = new ArrayList[26];
    for (int i = 0; i < S.length(); i++) {
      if (charIndexes[S.charAt(i) - 'a'] == null) {
        charIndexes[S.charAt(i) - 'a'] = new ArrayList<>();
      }
      charIndexes[S.charAt(i) - 'a'].add(i);
    }
    if (charIndexes[T.charAt(0) - 'a'] == null) {
      return "";
    }

    int minLength = Integer.MAX_VALUE;
    int resStartIndex = 0;

    for (int i = 0; i < charIndexes[T.charAt(0) - 'a'].size(); i++) {
      int startIndex = charIndexes[T.charAt(0) - 'a'].get(i) + 1;
      int lastIndex = getLastIndex(charIndexes, T, startIndex);
      if (lastIndex == -1) {
        continue;
      }
      if (lastIndex - startIndex + 1 < minLength) {
        minLength = lastIndex - startIndex + 1;
        resStartIndex = startIndex - 1;
      }
    }

    if (minLength == Integer.MAX_VALUE) {
      return "";
    }

    return S.substring(resStartIndex, resStartIndex + minLength);
  }


  private int getLastIndex(ArrayList<Integer>[] charIndexes, String T, int startIndex) {

    for (int i = 1; i < T.length(); i++) {
      int nextCharIndex = getMinIndex(charIndexes[T.charAt(i) - 'a'], startIndex);
      if (nextCharIndex == -1) {
        return -1;
      }
      startIndex = nextCharIndex + 1;
    }

    return startIndex;
  }

  private static int getMinIndex(ArrayList<Integer> indexes, int minIndex) {
    if (indexes == null) {
      return -1;
    }
    int low = 0;
    int high = indexes.size() - 1;
    while (low <= high) {
      int mid = (low + high) / 2;
      if (indexes.get(mid) > minIndex) {
        high = mid - 1;
      } else if (indexes.get(mid) < minIndex) {
        low = mid + 1;
      } else {
        return indexes.get(mid);
      }
    }
    if (low >= 0 && low < indexes.size()) {
      return indexes.get(low);
    }
    return -1;
  }
}