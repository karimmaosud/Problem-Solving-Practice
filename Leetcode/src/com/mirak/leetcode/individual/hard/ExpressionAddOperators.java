package com.mirak.leetcode.individual.hard;

import java.util.LinkedList;
import java.util.List;

public class ExpressionAddOperators {

  private static StringBuilder[] ops = {
    new StringBuilder("+"), new StringBuilder("-"), new StringBuilder("*")
  };

  public static List<String> addOperators(String num, int target) {
    LinkedList<String> res = new LinkedList<>();
    if (num.length() == 0) {
      return res;
    }
    solve(num.toCharArray(), new LinkedList<>(), 0, target, 0, '\0', 0, res);
    return res;
  }

  private static void solve(
      char[] numChars,
      LinkedList<StringBuilder> currentList,
      int index,
      int target,
      long currentSum,
      char prevOp,
      long prevSum,
      List<String> res) {

    if (index == numChars.length) {
      if (target == currentSum) {
        res.add(String.join("", currentList));
      }
      return;
    }

    int end = numChars[index] == '0' ? index : numChars.length - 1;
    StringBuilder builder = new StringBuilder();
    long runnerValue = 0;
    for (int i = index; i <= end; i++) {
      builder.append(numChars[i]);
      runnerValue = runnerValue * 10 + (numChars[i] - '0');
      if (index == 0) {
        currentList.addLast(builder);
        solve(numChars, currentList, i + 1, target, runnerValue, '\0', runnerValue, res);
        currentList.removeLast();
      } else {
        for (int j = 0; j < ops.length; j++) {
          currentList.addLast(ops[j]);
          currentList.addLast(builder);

          if (ops[j].charAt(0) == '*') {

            if (prevOp == '\0') {
              solve(
                  numChars,
                  currentList,
                  i + 1,
                  target,
                  currentSum * runnerValue,
                  '\0',
                  prevSum,
                  res);
            } else {
              long a = prevOp == '+' ? currentSum - prevSum : currentSum + prevSum;
              long newCurrentSum =
                  prevOp == '+' ? a + prevSum * runnerValue : a - prevSum * runnerValue;
              solve(
                  numChars,
                  currentList,
                  i + 1,
                  target,
                  newCurrentSum,
                  prevOp,
                  prevSum * runnerValue,
                  res);
            }

          } else {
            long newCurrentSum =
                ops[j].charAt(0) == '+' ? currentSum + runnerValue : currentSum - runnerValue;
            solve(
                numChars,
                currentList,
                i + 1,
                target,
                newCurrentSum,
                ops[j].charAt(0),
                runnerValue,
                res);
          }

          currentList.removeLast();
          currentList.removeLast();
        }
      }
    }
  }
}
