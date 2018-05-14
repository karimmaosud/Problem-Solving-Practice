package com.mirak.leetcode.contests.contest77;

public class NumberofLinesToWriteString {
  private final int MAX_WIDTH = 100;
  public int[] numberOfLines(int[] widths, String S) {
    int lines = 0;
    int currentLength = 0;
    for(char a: S.toCharArray()) {
      if(currentLength + widths[a - 'a'] > MAX_WIDTH) {
        lines++;
        currentLength = 0;
      }
      currentLength += widths[a - 'a'];
    }
    int[] res = new int[2];
    res[0] = lines + 1;
    res[1] = currentLength;
    return res;
  }
}




