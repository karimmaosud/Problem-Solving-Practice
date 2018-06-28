package com.mirak.leetcode.contests.contest90;

import java.util.ArrayList;

public class BuddyStrings {
  public boolean buddyStrings(String A, String B) {
    if(A.length() != B.length()) {
      return false;
    }

    if(A.isEmpty() || B.isEmpty()) {
      return false;
    }

    ArrayList<Integer> indexes = new ArrayList<>();
    for(int i = 0; i < A.length(); i++) {
      if(A.charAt(i) != B.charAt(i)) {
        indexes.add(i);
      }
    }
    if(indexes.isEmpty()) {
      // same strings, check for any character that appeared at least twice.
      int[] occurrence = new int[26];
      for(char c: A.toCharArray()) {
        occurrence[c - 'a']++;
        if(occurrence[c - 'a'] > 1) {
          return true;
        }
      }
      return false;
    }else if(indexes.size() == 2) {
      int idx1 = indexes.get(0);
      int idx2 = indexes.get(1);
      if(A.charAt(idx1) == B.charAt(idx2) && A.charAt(idx2) == B.charAt(idx1)) {
        return true;
      }
      return false;
    }
    return false;
  }
}
