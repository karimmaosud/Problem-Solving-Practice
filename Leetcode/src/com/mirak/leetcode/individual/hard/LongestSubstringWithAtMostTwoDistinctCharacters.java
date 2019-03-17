package com.mirak.leetcode.individual.hard;

import java.util.*;

public class LongestSubstringWithAtMostTwoDistinctCharacters {

  public static int lengthOfLongestSubstringTwoDistinct(String s) {
    // ceccbcc
    Map<Character, Integer> charCount = new HashMap<>();
    int left = 0, right = 0;
    int ans = 0;
    while (right < s.length()) {
      char rightChar = s.charAt(right);
      char leftChar = s.charAt(left);
      if (charCount.size() < 2 || charCount.containsKey(rightChar)) {
        charCount.put(rightChar, charCount.getOrDefault(rightChar, 0) + 1);
        right++;
      } else {
        charCount.put(leftChar, charCount.get(leftChar) - 1);
        if (charCount.get(leftChar) == 0) {
          charCount.remove(leftChar);
        }
        left++;
      }
      ans = Math.max(ans, right - left);
    }
    return ans;
  }

  public static void main(String[] args) {
    System.out.println(lengthOfLongestSubstringTwoDistinct("ceccbcb"));
  }

}
