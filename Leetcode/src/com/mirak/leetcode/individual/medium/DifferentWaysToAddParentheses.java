package com.mirak.leetcode.individual.medium;

import java.util.*;

public class DifferentWaysToAddParentheses {

  public List<Integer> diffWaysToCompute(String input) {
    ArrayList<Integer>[][] dp = new ArrayList[input.length()][input.length()];
    return solve(0, input.length() - 1, input, dp);
  }

  public ArrayList<Integer> solve(int i, int j, String input, ArrayList<Integer>[][] dp) {
    if (dp[i][j] != null) {
      return dp[i][j];
    }

    ArrayList<Integer> result = new ArrayList<>();
    for (int k = i + 1; k <= j; k++) {
      if (isOp(input.charAt(k))) {
        ArrayList<Integer> left = solve(i, k - 1, input, dp);
        ArrayList<Integer> right = solve(k + 1, j, input, dp);
        for (int a : left) {
          for (int b : right) {
            result.add(doOperation(a, b, input.charAt(k)));
          }
        }
      }
    }
    if (result.isEmpty()) {
      result.add(Integer.parseInt(input.substring(i, j + 1)));
    }
    return dp[i][j] = result;
  }

  private boolean isOp(char a) {
    return a == '+' || a == '-' || a == '*';
  }

  private int doOperation(int left, int right, char op) {
    return op == '+' ? left + right : op == '-' ? left - right : left * right;
  }

}
