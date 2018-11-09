package com.mirak.leetcode.individual.easy;


public class SymmetricTree {

  private class TreeNode {

    int val;
    TreeNode left, right;

    TreeNode(int val) {
      this.val = val;
      left = right = null;
    }
  }

  public boolean isSymmetric(TreeNode root) {
    return isSymmetric(root, root);
  }

  private boolean isSymmetric(TreeNode node1, TreeNode node2) {
    if (node1 == null && node2 == null) {
      return true;
    }
    if (node1 == null || node2 == null || node1.val != node2.val) {
      return false;
    }
    return isSymmetric(node1.left, node2.right) && isSymmetric(node1.right, node2.left);
  }
}
