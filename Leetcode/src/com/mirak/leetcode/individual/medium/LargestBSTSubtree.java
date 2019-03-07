package com.mirak.leetcode.individual.medium;

public class LargestBSTSubtree {

  private class TreeNode {

    TreeNode left, right;
    int val;

    TreeNode(int val) {
      this.val = val;
    }
  }

  private class IntegerWrapper {

    int value;

    IntegerWrapper() {
      this.value = 0;
    }
  }

  public int largestBSTSubtree(TreeNode root) {
    if (root == null) {
      return 0;
    }
    IntegerWrapper wrapper = new IntegerWrapper();
    findLargestBSTSubtree(root, wrapper);
    return Math.max(wrapper.value, 1);
  }

  private int[] findLargestBSTSubtree(TreeNode node, IntegerWrapper wrapper) {
    if (node == null) {
      return new int[]{Integer.MAX_VALUE, Integer.MIN_VALUE, 0};
    }

    int[] leftSubtree = findLargestBSTSubtree(node.left, wrapper);
    int[] rightSubtree = findLargestBSTSubtree(node.right, wrapper);
    if (node.val > leftSubtree[1] && node.val < rightSubtree[0]) {

      int subtreeSize = 1 + leftSubtree[2] + rightSubtree[2];
      wrapper.value = Math.max(wrapper.value, subtreeSize);

      return new int[]{
          Math.min(node.val, leftSubtree[0]), Math.max(rightSubtree[1], node.val), subtreeSize};
    }
    return new int[]{Integer.MIN_VALUE, Integer.MAX_VALUE, 0};
  }
}
