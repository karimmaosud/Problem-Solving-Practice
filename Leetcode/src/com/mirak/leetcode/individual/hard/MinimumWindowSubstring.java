package com.mirak.leetcode.individual.hard;

import java.util.*;

public class MinimumWindowSubstring {

  public String minWindow(String s, String t) {
    int left = 0;
    int right = 0;
    Map<Character, Integer> tCharCount = new HashMap<>();
    Map<Character, Integer> runningCountMap = new HashMap<>();
    initCountMap(tCharCount, t);
    int count = 0;
    int minLength = Integer.MAX_VALUE;
    int l = -1;
    while (left < s.length()) {
      if (count == t.length()) {
        int len = right - left;
        if (len < minLength) {
          l = left;
          minLength = len;
        }
        decrementOccurrence(s.charAt(left), runningCountMap);
        if (runningCountMap.getOrDefault(s.charAt(left), 0) < tCharCount
            .getOrDefault(s.charAt(left), 0)) {
          count--;
        }
        left++;
      } else {
        if (right == s.length()) {
          break;
        }
        if (runningCountMap.getOrDefault(s.charAt(right), 0) < tCharCount
            .getOrDefault(s.charAt(right), 0)) {
          count++;
        }
        incrementOccurrence(s.charAt(right), runningCountMap);
        right++;
      }
    }
    if (l == -1) {
      return "";
    }
    return s.substring(l, l + minLength);
  }

  private void initCountMap(Map<Character, Integer> countMap, String t) {
    for (char a : t.toCharArray()) {
      countMap.put(a, countMap.getOrDefault(a, 0) + 1);
    }
  }

  private void incrementOccurrence(char a, Map<Character, Integer> countMap) {
    countMap.put(a, countMap.getOrDefault(a, 0) + 1);
  }

  private void decrementOccurrence(char a, Map<Character, Integer> countMap) {
    if (!countMap.containsKey(a)) {
      return;
    }
    countMap.put(a, countMap.get(a) - 1);
  }
}
