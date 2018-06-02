package com.mirak.leetcode.individual.easy;

import java.util.LinkedList;

public class LongestUnivaluePath {
  class TreeNode{
    int val;
    TreeNode left;
    TreeNode right;
  }

  private int res = 1;
  public int longestUnivaluePath(TreeNode root) {

    if(root == null) {
      return 0;
    }
    solve(root);
    return res - 1;
  }

  private int solve(TreeNode node) {

    if(node == null) {
      return 0;
    }

    int leftPathLength = solve(node.left);
    int rightPathLength = solve(node.right);
    int maxPathDown = 1;

    if(node.left != null && node.left.val == node.val) {
      maxPathDown = 1 + leftPathLength;
    }
    if(node.right != null && node.right.val == node.val) {
      maxPathDown = Math.max(maxPathDown, 1 + rightPathLength);
    }

    res = Math.max(res, maxPathDown);
    if(node.left != null && node.right != null && node.left.val == node.val && node.right.val == node.val) {
      res = Math.max(res, 1 + leftPathLength + rightPathLength);
    }

    return maxPathDown;

  }

}
