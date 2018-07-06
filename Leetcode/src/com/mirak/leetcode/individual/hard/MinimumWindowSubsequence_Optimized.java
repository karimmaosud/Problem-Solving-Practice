package com.mirak.leetcode.individual.hard;

public class MinimumWindowSubsequence_Optimized {

  public String minWindow(String S, String T) {

    int[][] lastIndex = new int[2][S.length()];
    int[][] len = new int[2][S.length()];
    int idx = 0;
    resetLevel(lastIndex, len, idx + 1);
    resetLevel(lastIndex, len, idx);
    for (int i = 0; i < T.length(); i++) {
      for (int j = 0; j < S.length(); j++) {
        if (S.charAt(j) == T.charAt(i)) {

          if (i == 0) {
            lastIndex[idx][j] = j;
            len[idx][j] = 1;
            continue;
          }

          if (j == 0 || lastIndex[(idx + 1) % 2][j - 1] == -1) {
            continue;
          }

          lastIndex[idx][j] = j;
          // previous length + difference between i and last occurrence.
          len[idx][j] = len[(idx + 1) % 2][j - 1] + (j - lastIndex[(idx + 1) % 2][j - 1]);

        } else if (j > 0) {
          lastIndex[idx][j] = lastIndex[idx][j - 1];
          len[idx][j] = len[idx][j - 1];
        }
      }
      idx = (idx + 1) % 2;
      resetLevel(lastIndex, len, idx);
    }
    int minLength = Integer.MAX_VALUE;
    int startIndex = -1;
    for (int i = 0; i < S.length(); i++) {
      int currentLength = len[(idx + 1) % 2][i];
      if (currentLength != -1 && currentLength < minLength) {
        minLength = currentLength;
        startIndex = i - currentLength + 1;
      }
    }
    if (startIndex == -1) {
      return "";
    }
    return S.substring(startIndex, startIndex + minLength);
  }

  private void resetLevel(int[][] lastIndex, int[][] len, int level) {
    for (int i = 0; i < len[level].length; i++) {
      lastIndex[level][i] = len[level][i] = -1;
    }
  }
}