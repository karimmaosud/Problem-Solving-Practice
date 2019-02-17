package com.mirak.leetcode.contests.contest124;

import java.util.*;

public class CousinsInBinaryTree {

  private class TreeNode {

    TreeNode left, right;
    int val;
  }

  public boolean isCousins(TreeNode root, int x, int y) {
    Map<Integer, Integer> nodeLevel = new HashMap<>();
    boolean ans = traverseTree(root, x, y, nodeLevel, 0);
    if (!nodeLevel.containsKey(x) || !nodeLevel.containsKey(y)) {
      return false;
    }
    return nodeLevel.get(x).equals(nodeLevel.get(y)) && ans;
  }

  private boolean traverseTree(TreeNode node, int x, int y, Map<Integer, Integer> nodeLevel,
      int level) {
    if (node == null) {
      return true;
    }
    if (isSameParent(node, x, y)) {
      return false;
    }
    if (node.val == x || node.val == y) {
      nodeLevel.put(node.val, level);
    }
    return traverseTree(node.left, x, y, nodeLevel, level + 1) && traverseTree(node.right, x, y,
        nodeLevel, level + 1);
  }

  private boolean isSameParent(TreeNode node, int x, int y) {
    return node.left != null && node.right != null && (node.left.val == x || node.left.val == y)
        && (node.right.val == x || node.right.val == y);
  }
}
