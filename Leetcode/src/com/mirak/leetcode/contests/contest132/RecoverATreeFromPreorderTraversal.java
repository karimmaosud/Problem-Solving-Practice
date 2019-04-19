package com.mirak.leetcode.contests.contest132;

import java.util.*;

public class RecoverATreeFromPreorderTraversal {

  private class TreeNode {

    TreeNode left, right;
    int val;

    TreeNode(int val) {
      this.val = val;
    }
  }

  public TreeNode recoverFromPreorder(String S) {
    if (S == null || S.isEmpty()) {
      return null;
    }
    int idx = 0;
    Stack<TreeNode> stack = new Stack<>();
    while (idx < S.length()) {
      int level = 0;
      while (idx + level < S.length() && S.charAt(idx + level) == '-') {
        level++;
      }
      int j = idx + level;
      while (j < S.length() && Character.isDigit(S.charAt(j))) {
        j++;
      }

      TreeNode node = new TreeNode(Integer.parseInt(S.substring(idx + level, j)));

      if (level != 0) {
        while (stack.size() > level) {
          stack.pop();
        }
        if (stack.peek().left == null) {
          stack.peek().left = node;
        } else {
          stack.peek().right = node;
        }
      }
      stack.push(node);
      idx = j;
    }
    while (stack.size() != 1) {
      stack.pop();
    }
    return stack.pop();
  }
}
