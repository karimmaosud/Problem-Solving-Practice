package com.mirak.leetcode.individual.easy;

public class ClosestBinarySearchTreeValue {

  private class TreeNode {

    int val;
    TreeNode left, right;

    TreeNode(int val) {
      this.val = val;
    }
  }

  public int closestValue(TreeNode root, double target) {
    TreeNode successor = new TreeNode(Integer.MAX_VALUE);
    TreeNode predecessor = new TreeNode(Integer.MAX_VALUE);
    traverseTree(root, target, successor, predecessor);

    if (successor.val == Integer.MAX_VALUE) {
      return predecessor.val;
    } else if (predecessor.val == Integer.MAX_VALUE) {
      return successor.val;
    }

    if (Math.abs(target - successor.val) < Math.abs(target - predecessor.val)) {
      return successor.val;
    }
    return predecessor.val;
  }

  private void traverseTree(TreeNode node, double target, TreeNode successor,
      TreeNode predecessor) {
    if (node == null) {
      return;
    }

    if (Math.abs(target - node.val) <= 1e-9) {
      successor.val = node.val;
      return;
    }

    if (target > node.val) {
      successor.val = node.val;
      traverseTree(node.right, target, successor, predecessor);
    } else {
      predecessor.val = node.val;
      traverseTree(node.left, target, successor, predecessor);
    }
  }
}
