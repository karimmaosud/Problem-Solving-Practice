package com.mirak.leetcode.individual.hard;

public class LongestSubstringWithAtMostKDistinctCharacters {
  public int lengthOfLongestSubstringKDistinct(String s, int k) {

    int[] charCount = new int[256];
    int charsInWindow = 0;
    int l = 0, r = 0;
    char[] sChars = s.toCharArray();
    int len = 0;

    while (r < sChars.length) {
      if (charsInWindow <= k) {
        charCount[sChars[r]]++;
        if (charCount[sChars[r]] == 1) {
          charsInWindow++;
        }
        r++;
      } else {
        charCount[sChars[l]]--;
        if (charCount[sChars[l]] == 0) {
          charsInWindow--;
        }
        l++;
      }
      if (charsInWindow <= k) {
        len = Math.max(len, r - l);
      }
    }

    return len;
  }
}
