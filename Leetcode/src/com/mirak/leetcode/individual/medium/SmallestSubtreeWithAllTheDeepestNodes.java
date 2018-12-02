package com.mirak.leetcode.individual.medium;

public class SmallestSubtreeWithAllTheDeepestNodes {

  private class MaxCountWrapper {

    int max, count;

    MaxCountWrapper() {
      this.max = Integer.MIN_VALUE;
      this.count = 0;
    }

    void resetWithNewMax(int max) {
      this.max = max;
      this.count = 1;
    }

    void incrementCount() {
      this.count++;
    }

    void decrementCount() {
      this.count--;
    }
  }

  private class TreeNode {

    TreeNode left, right;
    int val;

    TreeNode(int val) {
      this.val = val;
    }
  }

  public TreeNode subtreeWithAllDeepest(TreeNode root) {
    MaxCountWrapper maxCountWrapper = new MaxCountWrapper();
    setMaxDepthCount(root, 0, maxCountWrapper);
    TreeNode ret = new TreeNode(-1);
    getDeepNodesCount(root, 0, maxCountWrapper, ret);
    return ret.left;
  }

  private void setMaxDepthCount(TreeNode node, int depth, MaxCountWrapper maxCountWrapper) {
    if (node == null) {
      return;
    }

    if (depth > maxCountWrapper.max) {
      maxCountWrapper.resetWithNewMax(depth);
    } else if (depth == maxCountWrapper.max) {
      maxCountWrapper.incrementCount();
    }
    setMaxDepthCount(node.left, depth + 1, maxCountWrapper);
    setMaxDepthCount(node.right, depth + 1, maxCountWrapper);
  }

  private int getDeepNodesCount(TreeNode node, int depth, MaxCountWrapper maxCountWrapper,
      TreeNode ret) {
    if (node == null) {
      return 0;
    }

    if (node.left == null && node.right == null) {
      int retCount = depth == maxCountWrapper.max ? 1 : 0;
      if (retCount == maxCountWrapper.count) {
        ret.left = node;
      }
      return retCount;
    }

    int leftCount = getDeepNodesCount(node.left, depth + 1, maxCountWrapper, ret);
    if (ret.left != null) {
      return 0;
    }

    int rightCount = getDeepNodesCount(node.right, depth + 1, maxCountWrapper, ret);
    if (ret.left != null) {
      return 0;
    }

    if (leftCount + rightCount == maxCountWrapper.count) {
      ret.left = node;
    }
    return leftCount + rightCount;
  }
}
