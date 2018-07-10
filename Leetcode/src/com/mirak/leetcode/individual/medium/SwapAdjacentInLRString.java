package com.mirak.leetcode.individual.medium;

public class SwapAdjacentInLRString {

  public boolean canTransform(String start, String end) {
    if (start.length() != end.length()) {
      return false;
    }
    if (start.length() == 0) {
      return true;
    }
    return transform(start, end);
  }

  private boolean transform(String start, String end) {
    int p1 = 0, p2 = adjustEndPointer(p1, 0, start);
    int pEnd = 0;
    boolean[] vis = new boolean[start.length()];
    while (p1 < start.length() && pEnd < end.length()) {
      if (start.charAt(p1) == end.charAt(pEnd)) {
        // advance both start and end pointers
        p1 = adJustStartPointer(p1 + 1, vis);
        p2 = adjustEndPointer(p1, p2, start);
        pEnd++;
        continue;
      }
      char[] possibleStart = {'R', 'X'};
      char[] possibleEnd = {'X', 'L'};
      int i;
      for (i = 0; i < possibleStart.length; i++) {
        if (start.charAt(p1) == possibleStart[i] && end.charAt(pEnd) == possibleEnd[i]
            && checkNextPointer(p2, start, possibleEnd[i])) {
          vis[p2++] = true;
          p2 = adjustEndPointer(p1, p2, start);
          pEnd++;
          break;
        }
      }
      if (i == 2) {
        return false;
      }
    }
    return p1 == pEnd;
  }

  private int adJustStartPointer(int p, boolean[] vis) {
    while (p < vis.length && vis[p]) {
      p++;
    }
    return p;
  }

  private int adjustEndPointer(int p1, int p2, String str) {
    if (p2 < p1) {
      p2 = p1;
    }
    while (p2 < str.length() && str.charAt(p2) == str.charAt(p1)) {
      p2++;
    }
    return p2;
  }

  private boolean checkNextPointer(int pointer, String str, char expected) {
    return pointer < str.length() && str.charAt(pointer) == expected;
  }
}