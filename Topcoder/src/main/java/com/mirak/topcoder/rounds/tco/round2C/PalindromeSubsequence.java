package com.mirak.topcoder.rounds.tco.round2C;

public class PalindromeSubsequence {
  public int[] optimalPartition(String s) {
    int[] res = new int[s.length()];
    for(int i = 0; i < s.length(); i++) {
      res[i] = 1;
    }
    if(isPalindrome(s)) {
      return res;
    }
    for(int i = 0; i < s.length(); i++) {
      if (s.charAt(i) == 'b') {
        res[i] = 2;
      }
    }
    return res;
  }

  private boolean isPalindrome(String s) {
    int i = 0, j = s.length() - 1;
    while (i < j) {
      if(s.charAt(i) != s.charAt(j)) {
        return false;
      }
      i++;
      j--;
    }
    return true;
  }
}
