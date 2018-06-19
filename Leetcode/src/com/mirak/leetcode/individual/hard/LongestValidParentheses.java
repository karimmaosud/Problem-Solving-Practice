package com.mirak.leetcode.individual.hard;

public class LongestValidParentheses {
  public int longestValidParentheses(String s) {
    int res = 0;
    int counter = 0;
    int left = 0;
    for(int i = 0; i < s.length(); i++) {
      counter += s.charAt(i) == '('? 1: -1;
      if(counter < 0) {
        res = Math.max(res, getLongestValidLength(s, left, i));
        counter = 0;
        left = i + 1;
      }
    }

    if(left < s.length()) {
      res = Math.max(res, getLongestValidLength(s, left, s.length()));
    }

    return res;
  }

  private int getLongestValidLength(String s, int left, int right) {
    right--;
    while(right >= left && s.charAt(right) == '(') {
      right--;
    }

    int runner = right;
    int counter = 0;
    int len = 0;

    while(runner >= left) {
      counter += s.charAt(runner) == '('? -1: 1;
      if(counter == 0) {
        len = Math.max(len, right - runner + 1);
      }
      if(counter < 0) {
        counter = 0;
        right = runner - 1;
      }
      runner--;
    }
    return len;
  }
}
