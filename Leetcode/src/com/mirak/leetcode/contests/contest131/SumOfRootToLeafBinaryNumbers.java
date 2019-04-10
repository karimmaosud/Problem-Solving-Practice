package com.mirak.leetcode.contests.contest131;

public class SumOfRootToLeafBinaryNumbers {

  private class TreeNode {

    TreeNode left, right;
    int val;
  }

  public int sumRootToLeaf(TreeNode root) {
    return sumPaths(root, 0);
  }

  private int sumPaths(TreeNode node, int prev) {
    if (node == null) {
      return 0;
    }
    if (isLeaf(node)) {
      return prev * 2 + node.val;
    }
    return sumPaths(node.left, prev * 2 + node.val) + sumPaths(node.right, prev * 2 + node.val);
  }


  private boolean isLeaf(TreeNode node) {

    return node != null && node.left == null && node.right == null;
  }

}
