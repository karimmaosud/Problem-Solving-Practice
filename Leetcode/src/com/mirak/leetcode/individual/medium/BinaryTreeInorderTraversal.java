package com.mirak.leetcode.individual.medium;


import java.util.*;

public class BinaryTreeInorderTraversal {

  private class TreeNode {

    TreeNode left, right;
    int val;
  }

  public List<Integer> inorderTraversal(TreeNode root) {
    List<Integer> inorderList = new ArrayList<>();
    if (root == null) {
      return inorderList;
    }
    Stack<TreeNode> stack = new Stack<>();
    addLeftNodes(root, stack);
    while (!stack.isEmpty()) {
      TreeNode peek = stack.pop();
      inorderList.add(peek.val);
      addLeftNodes(peek.right, stack);
    }
    return inorderList;
  }

  private void addLeftNodes(TreeNode node, Stack<TreeNode> stack) {
    if (node == null) {
      return;
    }
    stack.add(node);
    while (node.left != null) {
      stack.add(node.left);
      node = node.left;
    }
  }
}
