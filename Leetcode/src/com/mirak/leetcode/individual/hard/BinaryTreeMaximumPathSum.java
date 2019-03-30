package com.mirak.leetcode.individual.hard;

public class BinaryTreeMaximumPathSum {

  class TreeNode {

    TreeNode left, right;
    int val;
  }

  private final int INF = 1000000000;
  private int ans = Integer.MIN_VALUE;

  public int maxPathSum(TreeNode root) {
    if (root == null) {
      return 0;
    }
    solve(root);
    return ans;
  }

  private int solve(TreeNode node) {

    if (node == null) {
      return -INF;
    }

    int downLeft = solve(node.left);
    int downRight = solve(node.right);

    int nodeDownPath = node.val;

    nodeDownPath = Math.max(nodeDownPath, node.val + downLeft);

    nodeDownPath = Math.max(nodeDownPath, node.val + downRight);

    ans = Math.max(ans, Math.max(nodeDownPath, node.val + downLeft + downRight));

    return nodeDownPath;
  }
}
