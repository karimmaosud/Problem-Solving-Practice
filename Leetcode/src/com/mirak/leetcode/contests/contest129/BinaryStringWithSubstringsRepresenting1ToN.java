package com.mirak.leetcode.contests.contest129;

import java.util.*;

public class BinaryStringWithSubstringsRepresenting1ToN {

  public boolean queryString(String S, int N) {

    int len = numLength(N);
    int n = S.length();
    if (n < len) {
      return false;
    }

    Set<Long> set = new HashSet<>();
    for (int i = 1; i <= len; i++) {
      getNumsOfLengthLen(S, i, N, set);
    }
    return set.size() == N;
  }

  private void getNumsOfLengthLen(String s, int len, int N, Set<Long> set) {
    long runnerNum = 0;
    long num = (1L << len) - 1;
    for (int i = 0; i < len; i++) {
      int bit = s.charAt(i) == '1' ? 1 : 0;
      runnerNum <<= 1;
      runnerNum = (runnerNum | bit) & num;
    }

    if (runnerNum > 0 && runnerNum <= N) {
      set.add(runnerNum);
    }

    for (int i = len; i < s.length(); i++) {
      int bit = s.charAt(i) == '1' ? 1 : 0;
      runnerNum <<= 1;
      runnerNum = (runnerNum | bit) & num;
      if (runnerNum > 0 && runnerNum <= N) {
        set.add(runnerNum);
      }
    }
  }

  private int numLength(int N) {
    int len = 0;
    while (N > 0) {
      N >>= 1;
      len++;
    }
    return len;
  }


}
