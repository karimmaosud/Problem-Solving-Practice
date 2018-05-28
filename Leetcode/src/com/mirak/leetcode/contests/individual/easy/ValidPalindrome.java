package com.mirak.leetcode.contests.individual.easy;

public class ValidPalindrome {
  public boolean isPalindrome(String s) {
    int i = 0;
    int j = s.length() - 1;
    while(i < j) {
      boolean i_alphanumeric = isAlphanumeric(s.charAt(i));
      boolean j_alphanumeric = isAlphanumeric(s.charAt(j));
      if(i_alphanumeric && j_alphanumeric) {
        if(Character.toLowerCase(s.charAt(i)) != Character.toLowerCase(s.charAt(j))){
          return false;
        }
        i++;
        j--;
        continue;
      }
      if(!i_alphanumeric) {
        i++;
      }
      if(!j_alphanumeric) {
        j--;
      }
    }
    return true;
  }

  private boolean isAlphanumeric(char a) {
    return Character.isDigit(a) || Character.isAlphabetic(a);
  }
}
