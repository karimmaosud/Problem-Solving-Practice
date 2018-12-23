package com.mirak.leetcode.contests.contest115;

import java.util.*;

public class CheckCompletenessOfABinaryTree {

  private class TreeNode {

    TreeNode left, right;
    int val;

  }

  public boolean isCompleteTree(TreeNode root) {

    if (root == null) {
      return true;
    }

    boolean lastLevel = false;

    Queue<TreeNode> q = new LinkedList<>();
    q.add(root);
    while (!q.isEmpty()) {

      TreeNode top = q.poll();

      if (lastLevel && (top.left != null || top.right != null)) {
        return false;
      }

      if (top.left == null) {
        if (top.right == null) {
          lastLevel = true;
        } else {
          return false;
        }
      } else {
        q.add(top.left);
        if (top.right == null) {
          lastLevel = true;
        } else {
          q.add(top.right);
        }
      }
    }
    return true;
  }
}
