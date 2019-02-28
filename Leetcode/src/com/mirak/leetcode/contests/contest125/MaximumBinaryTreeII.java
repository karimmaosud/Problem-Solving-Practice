package com.mirak.leetcode.contests.contest125;

public class MaximumBinaryTreeII {

  private class TreeNode {

    TreeNode left, right;
    int val;

    TreeNode(int val) {
      this.val = val;
    }
  }

  public TreeNode insertIntoMaxTree(TreeNode root, int val) {
    if (root == null) {
      return new TreeNode(val);
    }
    if (val > root.val) {
      TreeNode newRoot = new TreeNode(val);
      newRoot.left = root;
      return newRoot;
    }
    addNewValue(root, null, val);
    return root;
  }

  private void addNewValue(TreeNode node, TreeNode prev, int val) {
    if (node == null) {
      prev.right = new TreeNode(val);
      return;
    }
    if (val > node.val) {
      prev.right = new TreeNode(val);
      prev.right.left = node;
    } else {
      addNewValue(node.right, node, val);
    }
  }


}
