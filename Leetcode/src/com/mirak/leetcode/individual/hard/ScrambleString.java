package com.mirak.leetcode.individual.hard;

import java.util.*;

public class ScrambleString {

  private int[] count = new int[26];

  public boolean isScramble(String s1, String s2) {
    Map<String, Boolean> mem = new HashMap<>();
    return isScramble(s1, s2, mem);
  }

  private boolean isScramble(String s1, String s2, Map<String, Boolean> mem) {
    String hash = s1 + "#" + s2;
    if (mem.containsKey(hash)) {
      return mem.get(hash);
    }
    if (s1.equals(s2)) {
      mem.put(hash, true);
      return true;
    }
    if (!isAnagrams(s1, s2)) {
      mem.put(hash, false);
      return false;
    }
    // try all possible swaps.
    // try also not to swap.
    for (int l = 1; l < s1.length(); l++) {
      // try swap with length l.
      if (isScramble(s1.substring(0, l), s2.substring(s2.length() - l), mem)
          && isScramble(s1.substring(l), s2.substring(0, s2.length() - l), mem)) {
        mem.put(hash, true);
        return true;
      }
      if (isScramble(s1.substring(0, l), s2.substring(0, l), mem)
          && isScramble(s1.substring(l), s2.substring(l), mem)) {
        mem.put(hash, true);
        return true;
      }
    }
    mem.put(hash, false);
    return false;
  }

  private boolean isAnagrams(String s1, String s2) {
    Arrays.fill(count, 0);
    for (char a : s1.toCharArray()) {
      count[a - 'a']++;
    }
    for (char a : s2.toCharArray()) {
      count[a - 'a']--;
      if (count[a - 'a'] < 0) {
        return false;
      }
    }
    return true;
  }
}
