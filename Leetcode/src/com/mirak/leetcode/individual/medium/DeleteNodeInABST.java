package com.mirak.leetcode.individual.medium;

public class DeleteNodeInABST {

  private class TreeNode {

    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
      val = x;
    }
  }


  public TreeNode deleteNode(TreeNode root, int key) {

    if (root == null) {
      return null;
    }

    TreeNode keyNode = searchNode(root, key);
    if (keyNode == null) {
      // If key is not in tree, just return root.
      return root;
    }

    TreeNode parent = findParent(root, keyNode);

    if (leafNode(keyNode)) {
      if (parent == null) {
        return null;
      }
      // Set parent's left/right pointer to null.
      adjustParentPointer(parent, keyNode, null);
      return root;
    }

    if (oneChild(keyNode)) {
      if (parent == null) {
        return keyNode.left == null ? keyNode.right : keyNode.left;
      }
      // Set parent's left/right pointer to the keyNode's child
      adjustParentPointer(parent, keyNode, keyNode.left == null ? keyNode.right : keyNode.left);
      return root;
    }

    TreeNode successor = findSuccessor(keyNode);

    successor.left = keyNode.left;

    if (successor != keyNode.right) {
      TreeNode successorParent = findParent(root, successor);
      TreeNode keyNodeRight = keyNode.right; // r
      TreeNode successorRight = successor.right; // x

      successorParent.left = successorRight;
      successor.right = keyNodeRight;
    }

    if (parent == null) {
      return successor;
    }

    adjustParentPointer(parent, keyNode, successor);

    return root;
  }

  private TreeNode searchNode(TreeNode node, int key) {
    if (node == null) {
      return null;
    }
    if (node.val == key) {
      return node;
    }
    if (key < node.val) {
      return searchNode(node.left, key);
    }
    return searchNode(node.right, key);
  }

  private TreeNode findParent(TreeNode root, TreeNode keyNode) {
    if (root == keyNode) {
      // Root node.
      return null;
    }

    if (keyNode == root.left || keyNode == root.right) {
      return root;
    }

    if (keyNode.val < root.val) {
      return findParent(root.left, keyNode);
    }
    return findParent(root.right, keyNode);
  }

  private boolean leafNode(TreeNode node) {
    return node.left == null && node.right == null;
  }

  private boolean oneChild(TreeNode node) {
    return node.left == null || node.right == null;
  }

  private TreeNode findSuccessor(TreeNode node) {
    TreeNode successor = node.right;
    while (successor.left != null) {
      successor = successor.left;
    }
    return successor;
  }

  private void adjustParentPointer(TreeNode parent, TreeNode deletedNode, TreeNode replacedNode) {
    if (parent.left == deletedNode) {
      parent.left = replacedNode;
    } else {
      parent.right = replacedNode;
    }
  }
}
