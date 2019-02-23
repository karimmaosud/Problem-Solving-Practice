package com.mirak.leetcode.individual.hard;

import java.util.*;

public class _24Game {

  private char[] ops = {'*', '/', '+', '-'};

  public boolean judgePoint24(int[] nums) {
    ArrayList<Double> list = new ArrayList<>();
    for (int num : nums) {
      list.add(1.0 * num);
    }
    return solve(list);
  }

  private boolean solve(ArrayList<Double> list) {
    if (list.size() == 1) {
      return Math.abs(list.get(0) - 24.0) <= 1e-9;
    }
    for (int i = 0; i < list.size(); i++) {
      for (int j = 0; j < list.size(); j++) {

        if (i == j) {
          continue;
        }

        ArrayList<Double> next = new ArrayList<>();

        for (int k = 0; k < list.size(); k++) {
          if (k != j && k != i) {
            next.add(list.get(k));
          }
        }
        for (int k = 0; k < 4; k++) {
          double num = doOperation(list.get(i), list.get(j), ops[k]);
          if (!Double.isNaN(num)) {
            next.add(num);
            if (solve(next)) {
              return true;
            }
            next.remove(next.size() - 1);
          }
        }
      }
    }
    return false;
  }


  private double doOperation(double left, double right, char op) {
    switch (op) {
      case '*':
        return left * right;
      case '/':
        return left / right;
      case '+':
        return left + right;
      default:
        return left - right;
    }
  }
}
