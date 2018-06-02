package com.mirak.leetcode.individual.medium;

public class OneEditDistance {
  public boolean isOneEditDistance(String s, String t) {
    int lenDiff = Math.abs(s.length() - t.length());
    if(lenDiff > 1) {
      return false;
    }
    if(lenDiff == 1 && (s.length() == 0 || t.length() == 0)) {
      return true;
    }
    int p1 = 0, p2 = 0;
    int diff = 0;
    while(p1 < s.length() && p2 < t.length()) {
      if(s.charAt(p1) != t.charAt(p2)) {
        diff++;
        if(lenDiff != 0) {
          if(s.length() > t.length()) {
            p1++;
          }else{
            p2++;
          }
          continue;
        }
      }
      p1++;
      p2++;
    }
    if(diff == 0) {
      return p1 != s.length() || p2 != t.length();
    }
    return diff == 1;
  }
}
