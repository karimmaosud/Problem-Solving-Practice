  package com.mirak.leetcode.contests.contest110;

  public class RangeSumOfBST {

    private class TreeNode {

      int val;
      TreeNode left, right;
    }

    public int rangeSumBST(TreeNode root, int L, int R) {
      if (root == null || L > R) {
        return 0;
      }

      if (root.val >= L && root.val <= R) {
        return root.val + rangeSumBST(root.left, L, root.val - 1) + rangeSumBST(root.right, root.val + 1, R);
      }

      if (root.val < L) {
        return rangeSumBST(root.right, L, R);
      }

      return rangeSumBST(root.left, L, R);
    }
  }
