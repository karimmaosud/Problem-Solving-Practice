package com.mirak.leetcode.contests.contest104;

import java.util.*;

public class WordSubsets {

  public List<String> wordSubsets(String[] A, String[] B) {

    int[] charsMaxCount = new int[26];
    for (int i = 0; i < B.length; i++) {
      int[] charsCount = new int[26];
      for (char a : B[i].toCharArray()) {
        charsCount[a - 'a']++;
      }
      for (int j = 0; j < 26; j++) {
        charsMaxCount[j] = Math.max(charsMaxCount[j], charsCount[j]);
      }
    }

    List<String> res = new LinkedList<>();
    for (int i = 0; i < A.length; i++) {
      int[] charsCount = new int[26];
      for (char a : A[i].toCharArray()) {
        charsCount[a - 'a']++;
      }
      boolean add = true;
      for (int j = 0; j < 26; j++) {
        if (charsCount[j] < charsMaxCount[j]) {
          add = false;
          break;
        }
      }
      if (add) {
        res.add(A[i]);
      }
    }
    return res;
  }
}
