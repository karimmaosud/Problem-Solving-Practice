package com.mirak.leetcode.individual.medium;

import java.util.*;

public class GenerateParentheses {

  public List<String> generateParenthesis(int n) {
    List<String> res = new LinkedList<>();
    solve(0, 0, new char[2 * n], n, res);
    return res;
  }

  private void solve(int open, int close, char[] current, int n, List<String> res) {
    if (open + close == 2 * n) {
      res.add(new String(current));
      return;
    }
    if (open < n) {
      current[open + close] = '(';
      solve(open + 1, close, current, n, res);
    }

    if (close < open) {
      current[open + close] = ')';
      solve(open, close + 1, current, n, res);
    }
  }
}

