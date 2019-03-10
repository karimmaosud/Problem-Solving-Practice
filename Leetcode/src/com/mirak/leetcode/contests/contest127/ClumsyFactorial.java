package com.mirak.leetcode.contests.contest127;

public class ClumsyFactorial {

  private char[] ops = {'*', '/', '+'};

  public int clumsy(int N) {
    long res = 0;
    for (int i = N; i >= 1; i -= 4) {
      long prev = i == N ? i : -i;
      for (int j = 1; j < 4 && i - j >= 1; j++) {
        prev = doOperation(prev, i - j, ops[j - 1]);
      }
      res += prev;
    }
    return (int) res;
  }

  private long doOperation(long left, int right, char op) {
    if (op == '*') {
      return left * right;
    }
    if (op == '/') {
      return left / right;
    }
    return left + right;
  }
}
