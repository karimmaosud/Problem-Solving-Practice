package com.mirak.leetcode.individual.medium;

import java.util.*;

public class RestoreIPAddresses {

  public List<String> restoreIpAddresses(String s) {
    List<String> res = new LinkedList<>();
    char[] sChars = s.toCharArray();
    int n = s.length();
    for (int i = 0; i < upperBound(0, sChars); i++) {
      if (!isValidInt(s, 0, i)) {
        break;
      }
      for (int j = i + 1; j < upperBound(i + 1, sChars); j++) {
        if (!isValidInt(s, i + 1, j)) {
          break;
        }
        for (int k = j + 1; k < upperBound(j + 1, sChars); k++) {
          if (!isValidInt(s, j + 1, k)) {
            break;
          }

          if (!isValidInt(s, k + 1, n - 1) || (k + 1 < n && sChars[k + 1] == '0'
              && n - k - 1 != 1)) {
            continue;
          }
          res.add(s.substring(0, i + 1) + "." + s.substring(i + 1, j + 1) + "." + s
              .substring(j + 1, k + 1)
              + "." + s.substring(k + 1));
        }
      }
    }
    return res;
  }

  private int upperBound(int i, char[] sChars) {
    if (i == sChars.length) {
      return i;
    }
    return sChars[i] == '0' ? i + 1 : sChars.length;
  }

  private boolean isValidInt(String s, int start, int end) {
    return start <= end && end - start + 1 < 4
        && Integer.parseInt(s.substring(start, end + 1)) <= 255;
  }
}
