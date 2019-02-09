package com.mirak.leetcode.individual.medium;

import java.util.HashMap;
import java.util.Map;

public class BinaryTreeLongestConsecutiveSequenceII {

  private class TreeNode {

    TreeNode left, right;
    int val;
  }

  private int maxLen = 1;
  private Map<TreeNode, Integer> longestIncreasingPath;
  private Map<TreeNode, Integer> longestDecreasingPath;

  public int longestConsecutive(TreeNode root) {
    if (root == null) {
      return 0;
    }
    longestIncreasingPath = new HashMap<>();
    longestDecreasingPath = new HashMap<>();
    searchLongestConsecutivePath(root, longestIncreasingPath, longestDecreasingPath);
    return maxLen;
  }

  private void searchLongestConsecutivePath(TreeNode node,
      Map<TreeNode, Integer> longestIncreasingPath, Map<TreeNode, Integer> longestDecreasingPath) {

    longestIncreasingPath.put(node, 1);
    longestDecreasingPath.put(node, 1);

    TreeNode[] children = {node.left, node.right};
    for (TreeNode child : children) {
      if (child == null) {
        continue;
      }
      searchLongestConsecutivePath(child, longestIncreasingPath, longestDecreasingPath);
      if (child.val == node.val + 1) {
        longestIncreasingPath.put(node,
            Math.max(longestIncreasingPath.get(node), longestIncreasingPath.get(child) + 1));
      }
      if (child.val == node.val - 1) {
        longestDecreasingPath.put(node,
            Math.max(longestDecreasingPath.get(node), longestDecreasingPath.get(child) + 1));
      }
    }
    maxLen = Math
        .max(maxLen, Math.max(longestIncreasingPath.get(node), longestDecreasingPath.get(node)));

    if (node.left != null && node.right != null) {
      if (node.val == node.left.val + 1 && node.right.val == node.val + 1) {
        maxLen = Math.max(maxLen,
            longestDecreasingPath.get(node.left) + longestIncreasingPath.get(node.right) + 1);
      }
      if (node.val == node.left.val - 1 && node.right.val == node.val - 1) {
        maxLen = Math.max(maxLen,
            longestIncreasingPath.get(node.left) + longestDecreasingPath.get(node.right) + 1);
      }
    }
  }

}
