package com.mirak.leetcode.individual.easy;

public class BackspaceStringCompare {

  public boolean backspaceCompare(String S, String T) {
    int sRemoved = 0, tRemoved = 0;

    int ps = S.length() - 1, pt = T.length() - 1;
    while (ps >= 0 && pt >= 0) {
      if (S.charAt(ps) == '#') {
        sRemoved++;
        ps--;
        continue;
      }
      if (T.charAt(pt) == '#') {
        tRemoved++;
        pt--;
        continue;
      }
      if (sRemoved > 0) {
        sRemoved--;
        ps--;
        continue;
      }
      if (tRemoved > 0) {
        tRemoved--;
        pt--;
        continue;
      }
      if (S.charAt(ps) != T.charAt(pt)) {
        return false;
      }
      ps--;
      pt--;
    }
    return moveBack(ps, S, sRemoved) < 0 && moveBack(pt, T, tRemoved) < 0;
  }

  private int moveBack(int p, String s, int removed) {
    while (p >= 0) {
      if (s.charAt(p) == '#') {
        removed++;
        p--;
        continue;
      }

      if (removed > 0) {
        removed--;
        p--;
        continue;
      }
      return p;
    }
    return p;
  }
}
