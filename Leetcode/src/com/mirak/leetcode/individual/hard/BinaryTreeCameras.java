package com.mirak.leetcode.individual.hard;

import java.util.*;

public class BinaryTreeCameras {

  private class TreeNode {

    TreeNode left, right;
  }

  public int minCameraCover(TreeNode root) {
    Set<TreeNode> covered = new HashSet<>();
    int ans = dfs(root, null, covered);
    if (!covered.contains(root) && root != null) {
      return ans + 1;
    }
    return ans;
  }

  private int dfs(TreeNode node, TreeNode p, Set<TreeNode> covered) {

    if (node == null) {
      return 0;
    }

    int ans = dfs(node.left, node, covered) + dfs(node.right, node, covered);

    if ((node.left != null && !covered.contains(node.left)) || (node.right != null && !covered
        .contains(node.right))) {
      covered.add(node);
      if (p != null) {
        covered.add(p);
      }
      return ans + 1;
    }

    return ans;
  }
}