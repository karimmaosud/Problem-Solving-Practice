package com.mirak.leetcode.contests.contest79;

public class BinaryTreePruning {
  class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
      val = x;
    }
  }

  public TreeNode pruneTree(TreeNode root) {
    if(root == null) {
      return null;
    }

    prune(root);

    if(root.left == null && root.right == null && root.val == 0) {
      return null;
    }
    return root;
  }

  private int prune(TreeNode node) {
    if(node == null) {
      return 0;
    }
    int leftCount = prune(node.left);
    int rightCount = prune(node.right);
    if(leftCount == 0) {
      node.left = null;
    }
    if(rightCount == 0) {
      node.right = null;
    }
    return node.val + leftCount + rightCount;
  }
}
