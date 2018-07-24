package com.mirak.leetcode.contests.contest94;

import java.util.ArrayList;

public class LeafSimilarTrees {

  private class TreeNode {

    TreeNode left, right;
    int val;
  }

  public boolean leafSimilar(TreeNode root1, TreeNode root2) {
    ArrayList<Integer> leafs1 = new ArrayList<>();
    ArrayList<Integer> leafs2 = new ArrayList<>();
    traverseTree(root1, leafs1);
    traverseTree(root2, leafs2);
    if (leafs1.size() != leafs2.size()) {
      return false;
    }
    for (int i = 0; i < leafs1.size(); i++) {
      if (leafs1.get(i) != leafs2.get(i)) {
        return false;
      }
    }
    return true;
  }

  private void traverseTree(TreeNode node, ArrayList<Integer> leafs) {
    if (node == null) {
      return;
    }

    if (node.left == null && node.right == null) {
      leafs.add(node.val);
      return;
    }
    traverseTree(node.left, leafs);
    traverseTree(node.right, leafs);
  }
}
