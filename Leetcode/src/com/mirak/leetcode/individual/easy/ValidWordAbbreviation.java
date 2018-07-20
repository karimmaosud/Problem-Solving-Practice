package com.mirak.leetcode.individual.easy;

public class ValidWordAbbreviation {
  public boolean validWordAbbreviation(String word, String abbr) {
    int wordIndex = 0;
    int idx = 0;
    while(idx < abbr.length()) {
      if(Character.isDigit(abbr.charAt(idx))) {
        int num = 0;
        if (abbr.charAt(idx) == '0') {
          return false;
        }
        while(idx < abbr.length() && Character.isDigit(abbr.charAt(idx))) {
          num = num * 10 + (abbr.charAt(idx++) - '0');
        }
        wordIndex += num;
      }else{
        if(wordIndex >= word.length() || word.charAt(wordIndex) != abbr.charAt(idx)) {
          return false;
        }
        wordIndex++;
        idx++;
      }
    }
    return idx == abbr.length() && wordIndex == word.length();
  }
}
