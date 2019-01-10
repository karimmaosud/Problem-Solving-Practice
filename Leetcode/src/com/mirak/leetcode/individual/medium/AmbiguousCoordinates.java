package com.mirak.leetcode.individual.medium;

import java.util.*;

public class AmbiguousCoordinates {

  public List<String> ambiguousCoordinates(String S) {
    ArrayList<String> res = new ArrayList<>();
    for (int i = 1; i < S.length() - 1; i++) {
      ArrayList<String> leftNums = getAllValidNumbers(S, 1, i);
      ArrayList<String> rightNums = getAllValidNumbers(S, i + 1, S.length() - 2);
      for (String leftNum : leftNums) {
        for (String rightNum : rightNums) {
          res.add("(" + leftNum + ", " + rightNum + ")");
        }
      }
    }
    return res;
  }

  private ArrayList<String> getAllValidNumbers(String str, int left, int right) {
    ArrayList<String> res = new ArrayList<>();
    // Any 1-digit number is always valid.
    if (right - left + 1 == 1) {
      res.add(str.substring(left, right + 1));
      return res;
    }

    if (left > right) {
      return res;
    }

    // We have more than one digit.
    if (str.charAt(left) == '0') {
      if (str.charAt(right) != '0') {
        res.add("0." + str.substring(left + 1, right + 1));
      }
      return res;
    }

    // Any number that starts with a digit is always valid.
    res.add(str.substring(left, right + 1));
    if (str.charAt(right) != '0') {
      int j = right;
      while (j > left) {
        res.add(str.substring(left, j) + "." + str.substring(j, right + 1));
        j--;
      }
    }

    return res;
  }
}

