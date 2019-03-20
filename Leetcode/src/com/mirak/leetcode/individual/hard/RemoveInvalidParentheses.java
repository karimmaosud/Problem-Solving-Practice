package com.mirak.leetcode.individual.hard;

import java.util.*;


public class RemoveInvalidParentheses {

  public List<String> removeInvalidParentheses(String s) {
    int left = 0, right = 0;
    for (char a : s.toCharArray()) {
      if (a == '(') {
        left++;
      } else if (a == ')') {
        if (left == right) {
          continue;
        }
        right++;
      }
    }
    left = Math.min(left, right);
    Set<String> set = new HashSet<>();
    solve(s, 0, left, right, new LinkedList<>(), set);
    return new ArrayList<>(set);
  }

  private void solve(String s, int idx, int left, int right, LinkedList<Character> runnerString,
      Set<String> res) {

    if (right < left) {
      return;
    }

    if (idx == s.length()) {
      if (left > 0 || right > 0) {
        return;
      }
      StringBuilder builder = new StringBuilder();
      int size = runnerString.size();
      for (int i = 0; i < size; i++) {
        char first = runnerString.removeFirst();
        builder.append(first);
        runnerString.addLast(first);
      }
      res.add(builder.toString());
      return;
    }

    if (s.charAt(idx) != '(' && s.charAt(idx) != ')') {
      runnerString.addLast(s.charAt(idx));
      solve(s, idx + 1, left, right, runnerString, res);
      runnerString.removeLast();
      return;
    }
    if (s.charAt(idx) == '(') {
      if (left > 0) {
        // can take or leave.
        runnerString.addLast('(');
        solve(s, idx + 1, left - 1, right, runnerString, res);
        runnerString.removeLast();
      }
      solve(s, idx + 1, left, right, runnerString, res);
    } else {
      if (right > 0) {
        runnerString.addLast(')');
        solve(s, idx + 1, left, right - 1, runnerString, res);
        runnerString.removeLast();
      }
      solve(s, idx + 1, left, right, runnerString, res);
    }
  }
}
