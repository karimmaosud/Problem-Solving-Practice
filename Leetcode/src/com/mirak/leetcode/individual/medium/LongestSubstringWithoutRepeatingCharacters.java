package com.mirak.leetcode.individual.medium;

import java.util.*;

public class LongestSubstringWithoutRepeatingCharacters {

  public int lengthOfLongestSubstring(String s) {
    Set<Character> visChars = new HashSet<>();
    int left = 0;
    int right = 0;
    int max = 0;
    while (right < s.length()) {
      if (visChars.contains(s.charAt(right))) {
        while (s.charAt(left) != s.charAt(right)) {
          visChars.remove(s.charAt(left++));
        }
        left++;
      }
      visChars.add(s.charAt(right++));
      max = Math.max(max, visChars.size());
    }
    return max;
  }


}
