package com.mirak.leetcode.individual.medium;

public class LowestCommonAncestorOfABinaryTree {

  private class TreeNode {

    TreeNode left, right;
    int val;
  }

  private class NodeWrapper {

    TreeNode lca = null;
  }

  public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
    NodeWrapper lcaNodeWrapper = new NodeWrapper();
    findLCA(root, p, q, lcaNodeWrapper);
    return lcaNodeWrapper.lca;
  }

  private int findLCA(TreeNode node, TreeNode p, TreeNode q, NodeWrapper lcaNodeWrapper) {
    if (node == null) {
      return 0;
    }

    int value = node == p ? 1 : node == q ? 2 : 0;
    int leftValue = findLCA(node.left, p, q, lcaNodeWrapper);
    if (lcaNodeWrapper.lca != null) {
      return 0;
    }
    int rightValue = findLCA(node.right, p, q, lcaNodeWrapper);

    if ((value ^ leftValue ^ rightValue) == 3 && lcaNodeWrapper.lca == null) {
      lcaNodeWrapper.lca = node;
    }

    return value ^ leftValue ^ rightValue;
  }
}
