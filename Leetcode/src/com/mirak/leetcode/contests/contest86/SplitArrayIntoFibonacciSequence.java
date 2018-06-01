package com.mirak.leetcode.contests.contest86;

import java.util.LinkedList;
import java.util.List;

public class SplitArrayIntoFibonacciSequence {
  public List<Integer> splitIntoFibonacci(String S) {
    char[] sChars = S.toCharArray();

    long first = 0;
    int iEnd = sChars[0] == '0'? 1: sChars.length - 1;

    for(int i = 0; i < iEnd; i++) {

      first = first * 10 + (sChars[i] - '0');
      if(first > Integer.MAX_VALUE) {
        break;
      }

      long second = 0;
      int jEnd = sChars[i + 1] == '0'? i + 1: sChars.length - 1;
      for(int j = i + 1; j <= jEnd; j++) {
        second = second * 10 + (sChars[j] - '0');
        if(second > Integer.MAX_VALUE) {
          break;
        }

        LinkedList<Integer> list = new LinkedList<>();
        list.add((int) first);
        list.add((int) second);
        boolean canSplit = walk(first, second, S, j + 1, list);
        if(canSplit && list.size() > 2) {
          return list;
        }
      }
    }
    return new LinkedList<>();
  }

  private boolean walk(long first, long second, String s, int fromIndex, LinkedList<Integer> list) {
    if(fromIndex == s.length()) {
      return true;
    }
    long next = first + second;
    String nextStr = "" + next;
    if(next > Integer.MAX_VALUE || s.indexOf(nextStr, fromIndex) != fromIndex) {
      return false;
    }
    list.addLast((int) next);
    return walk(second, next, s, fromIndex + nextStr.length(), list);
  }
}
