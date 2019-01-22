package com.mirak.leetcode.individual.medium;

import java.util.*;

public class SwapAdjacentInLRString2 {

  public boolean canTransform(String start, String end) {
    Map<Character, Character> swapChar = new HashMap<>();
    swapChar.put('R', 'X');
    swapChar.put('X', 'L');

    if (start.length() != end.length()) {
      return false;
    }
    char[] startChars = start.toCharArray();
    int j = 1;
    for (int i = 0; i < startChars.length; i++) {
      if (j <= i) {
        j = i + 1;
      }
      if (startChars[i] == end.charAt(i)) {
        continue;
      }
      if (startChars[i] == 'L' || j == startChars.length) {
        return false;
      }
      while (j < startChars.length && startChars[j] == startChars[i]) {
        j++;
      }
      // j is the first mismatch.
      if (j == startChars.length || startChars[j] != swapChar.get(startChars[i])) {
        return false;
      }
      swap(startChars, i, j);
      i--;
    }
    return true;
  }

  private void swap(char[] chars, int i, int j) {
    char temp = chars[i];
    chars[i] = chars[j];
    chars[j] = temp;
  }


}
