package com.mirak.leetcode.contests.contest131;

public class RemoveOutermostParentheses {

  public String removeOuterParentheses(String S) {
    int counter = 0;
    int left = 0;
    StringBuilder builder = new StringBuilder();
    for (int i = 0; i < S.length(); ++i) {
      counter += (S.charAt(i) == '(' ? 1 : -1);
      if (counter == 0) {
        builder.append(S.substring(left + 1, i));
        left = i + 1;
      }
    }
    return builder.toString();
  }
}
