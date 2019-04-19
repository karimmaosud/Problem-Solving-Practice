package com.mirak.leetcode.contests.contest132;

public class MaximumDifferenceBetweenNodeAndAncestor {

  private class TreeNode {

    TreeNode left, right;
    int val;
  }

  public int maxAncestorDiff(TreeNode root) {
    return solve(root, root.val, root.val);
  }

  private int solve(TreeNode node, int max, int min) {
    if (node == null) {
      return max - min;
    }
    int res = Math.max(Math.abs(node.val - max), Math.abs(node.val - min));
    int nextMax = Math.max(node.val, max);
    int nextMin = Math.min(node.val, min);
    return Math.max(res,
        Math.max(solve(node.left, nextMax, nextMin), solve(node.right, nextMax, nextMin)));
  }

}
